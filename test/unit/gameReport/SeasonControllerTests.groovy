package gameReport



import org.junit.*

import gameReport.Season;
import gameReport.SeasonController;
import grails.test.mixin.*

@TestFor(SeasonController)
@Mock(Season)
class SeasonControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/season/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seasonInstanceList.size() == 0
        assert model.seasonInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.seasonInstance != null
    }

    void testSave() {
        controller.save()

        assert model.seasonInstance != null
        assert view == '/season/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/season/show/1'
        assert controller.flash.message != null
        assert Season.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/season/list'

        populateValidParams(params)
        def season = new Season(params)

        assert season.save() != null

        params.id = season.id

        def model = controller.show()

        assert model.seasonInstance == season
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/season/list'

        populateValidParams(params)
        def season = new Season(params)

        assert season.save() != null

        params.id = season.id

        def model = controller.edit()

        assert model.seasonInstance == season
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/season/list'

        response.reset()

        populateValidParams(params)
        def season = new Season(params)

        assert season.save() != null

        // test invalid parameters in update
        params.id = season.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/season/edit"
        assert model.seasonInstance != null

        season.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/season/show/$season.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        season.clearErrors()

        populateValidParams(params)
        params.id = season.id
        params.version = -1
        controller.update()

        assert view == "/season/edit"
        assert model.seasonInstance != null
        assert model.seasonInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/season/list'

        response.reset()

        populateValidParams(params)
        def season = new Season(params)

        assert season.save() != null
        assert Season.count() == 1

        params.id = season.id

        controller.delete()

        assert Season.count() == 0
        assert Season.get(season.id) == null
        assert response.redirectedUrl == '/season/list'
    }
}
