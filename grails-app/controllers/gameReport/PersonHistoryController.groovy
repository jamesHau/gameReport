package gameReport

import org.springframework.dao.DataIntegrityViolationException

class PersonHistoryController {
	static scaffold = true
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [personRecordInstanceList: PersonHistory.list(params), personRecordInstanceTotal: PersonHistory.count()]
    }

    def create() {
        [personRecordInstance: new PersonHistory(params)]
    }

    def save() {
        def personRecordInstance = new PersonHistory(params)
        if (!personRecordInstance.save(flush: true)) {
            render(view: "create", model: [personRecordInstance: personRecordInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), personRecordInstance.id])
        redirect(action: "show", id: personRecordInstance.id)
    }

    def show(Long id) {
        def personRecordInstance = PersonHistory.get(id)
        if (!personRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "list")
            return
        }

        [personRecordInstance: personRecordInstance]
    }

    def edit(Long id) {
        def personRecordInstance = PersonHistory.get(id)
        if (!personRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "list")
            return
        }

        [personRecordInstance: personRecordInstance]
    }

    def update(Long id, Long version) {
        def personRecordInstance = PersonHistory.get(id)
        if (!personRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (personRecordInstance.version > version) {
                personRecordInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'personRecord.label', default: 'PersonRecord')] as Object[],
                          "Another user has updated this PersonRecord while you were editing")
                render(view: "edit", model: [personRecordInstance: personRecordInstance])
                return
            }
        }

        personRecordInstance.properties = params

        if (!personRecordInstance.save(flush: true)) {
            render(view: "edit", model: [personRecordInstance: personRecordInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), personRecordInstance.id])
        redirect(action: "show", id: personRecordInstance.id)
    }

    def delete(Long id) {
        def personRecordInstance = PersonHistory.get(id)
        if (!personRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "list")
            return
        }

        try {
            personRecordInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'personRecord.label', default: 'PersonRecord'), id])
            redirect(action: "show", id: id)
        }
    }
}
