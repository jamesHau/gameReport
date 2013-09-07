package gameReport

import org.springframework.dao.DataIntegrityViolationException

class MatchRecordController {
	static scaffold = true
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [matchRecordInstanceList: MatchRecord.list(params), matchRecordInstanceTotal: MatchRecord.count()]
    }

    def create() {
        [matchRecordInstance: new MatchRecord(params)]
    }

    def save() {
        def matchRecordInstance = new MatchRecord(params)
        if (!matchRecordInstance.save(flush: true)) {
            render(view: "create", model: [matchRecordInstance: matchRecordInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), matchRecordInstance.id])
        redirect(action: "show", id: matchRecordInstance.id)
    }

    def show(Long id) {
        def matchRecordInstance = MatchRecord.get(id)
        if (!matchRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "list")
            return
        }

        [matchRecordInstance: matchRecordInstance]
    }

    def edit(Long id) {
        def matchRecordInstance = MatchRecord.get(id)
        if (!matchRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "list")
            return
        }

        [matchRecordInstance: matchRecordInstance]
    }

    def update(Long id, Long version) {
        def matchRecordInstance = MatchRecord.get(id)
        if (!matchRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (matchRecordInstance.version > version) {
                matchRecordInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'matchRecord.label', default: 'MatchRecord')] as Object[],
                          "Another user has updated this MatchRecord while you were editing")
                render(view: "edit", model: [matchRecordInstance: matchRecordInstance])
                return
            }
        }

        matchRecordInstance.properties = params

        if (!matchRecordInstance.save(flush: true)) {
            render(view: "edit", model: [matchRecordInstance: matchRecordInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), matchRecordInstance.id])
        redirect(action: "show", id: matchRecordInstance.id)
    }

    def delete(Long id) {
        def matchRecordInstance = MatchRecord.get(id)
        if (!matchRecordInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "list")
            return
        }

        try {
            matchRecordInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'matchRecord.label', default: 'MatchRecord'), id])
            redirect(action: "show", id: id)
        }
    }
}
