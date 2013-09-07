package gameReport

import gameReport.Season;

import org.springframework.dao.DataIntegrityViolationException

class SeasonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [seasonInstanceList: Season.list(params), seasonInstanceTotal: Season.count()]
    }

    def create() {
        [seasonInstance: new Season(params)]
    }

    def save() {
        def seasonInstance = new Season(params)
        if (!seasonInstance.save(flush: true)) {
            render(view: "create", model: [seasonInstance: seasonInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'season.label', default: 'Season'), seasonInstance.id])
        redirect(action: "show", id: seasonInstance.id)
    }

    def show(Long id) {
        def seasonInstance = Season.get(id)
        if (!seasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "list")
            return
        }

        [seasonInstance: seasonInstance]
    }

    def edit(Long id) {
        def seasonInstance = Season.get(id)
        if (!seasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "list")
            return
        }

        [seasonInstance: seasonInstance]
    }

    def update(Long id, Long version) {
        def seasonInstance = Season.get(id)
        if (!seasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (seasonInstance.version > version) {
                seasonInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'season.label', default: 'Season')] as Object[],
                          "Another user has updated this Season while you were editing")
                render(view: "edit", model: [seasonInstance: seasonInstance])
                return
            }
        }

        seasonInstance.properties = params

        if (!seasonInstance.save(flush: true)) {
            render(view: "edit", model: [seasonInstance: seasonInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'season.label', default: 'Season'), seasonInstance.id])
        redirect(action: "show", id: seasonInstance.id)
    }

    def delete(Long id) {
        def seasonInstance = Season.get(id)
        if (!seasonInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "list")
            return
        }

        try {
            seasonInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'season.label', default: 'Season'), id])
            redirect(action: "show", id: id)
        }
    }
}
