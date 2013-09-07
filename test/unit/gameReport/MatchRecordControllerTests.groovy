package gameReport



import org.junit.*
import grails.test.mixin.*

@TestFor(MatchRecordController)
@Mock(MatchRecord)
class MatchRecordControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/matchRecord/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.matchRecordInstanceList.size() == 0
        assert model.matchRecordInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.matchRecordInstance != null
    }

    void testSave() {
        controller.save()

        assert model.matchRecordInstance != null
        assert view == '/matchRecord/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/matchRecord/show/1'
        assert controller.flash.message != null
        assert MatchRecord.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/matchRecord/list'

        populateValidParams(params)
        def matchRecord = new MatchRecord(params)

        assert matchRecord.save() != null

        params.id = matchRecord.id

        def model = controller.show()

        assert model.matchRecordInstance == matchRecord
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/matchRecord/list'

        populateValidParams(params)
        def matchRecord = new MatchRecord(params)

        assert matchRecord.save() != null

        params.id = matchRecord.id

        def model = controller.edit()

        assert model.matchRecordInstance == matchRecord
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/matchRecord/list'

        response.reset()

        populateValidParams(params)
        def matchRecord = new MatchRecord(params)

        assert matchRecord.save() != null

        // test invalid parameters in update
        params.id = matchRecord.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/matchRecord/edit"
        assert model.matchRecordInstance != null

        matchRecord.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/matchRecord/show/$matchRecord.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        matchRecord.clearErrors()

        populateValidParams(params)
        params.id = matchRecord.id
        params.version = -1
        controller.update()

        assert view == "/matchRecord/edit"
        assert model.matchRecordInstance != null
        assert model.matchRecordInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/matchRecord/list'

        response.reset()

        populateValidParams(params)
        def matchRecord = new MatchRecord(params)

        assert matchRecord.save() != null
        assert MatchRecord.count() == 1

        params.id = matchRecord.id

        controller.delete()

        assert MatchRecord.count() == 0
        assert MatchRecord.get(matchRecord.id) == null
        assert response.redirectedUrl == '/matchRecord/list'
    }
}
