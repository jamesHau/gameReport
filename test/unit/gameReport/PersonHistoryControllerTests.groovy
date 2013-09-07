package gameReport



import org.junit.*
import grails.test.mixin.*

@TestFor(PersonHistoryController)
@Mock(PersonHistory)
class PersonHistoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/personRecord/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.personRecordInstanceList.size() == 0
        assert model.personRecordInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.personRecordInstance != null
    }

    void testSave() {
        controller.save()

        assert model.personRecordInstance != null
        assert view == '/personRecord/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/personRecord/show/1'
        assert controller.flash.message != null
        assert PersonHistory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/personRecord/list'

        populateValidParams(params)
        def personRecord = new PersonHistory(params)

        assert personRecord.save() != null

        params.id = personRecord.id

        def model = controller.show()

        assert model.personRecordInstance == personRecord
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/personRecord/list'

        populateValidParams(params)
        def personRecord = new PersonHistory(params)

        assert personRecord.save() != null

        params.id = personRecord.id

        def model = controller.edit()

        assert model.personRecordInstance == personRecord
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/personRecord/list'

        response.reset()

        populateValidParams(params)
        def personRecord = new PersonHistory(params)

        assert personRecord.save() != null

        // test invalid parameters in update
        params.id = personRecord.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/personRecord/edit"
        assert model.personRecordInstance != null

        personRecord.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/personRecord/show/$personRecord.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        personRecord.clearErrors()

        populateValidParams(params)
        params.id = personRecord.id
        params.version = -1
        controller.update()

        assert view == "/personRecord/edit"
        assert model.personRecordInstance != null
        assert model.personRecordInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/personRecord/list'

        response.reset()

        populateValidParams(params)
        def personRecord = new PersonHistory(params)

        assert personRecord.save() != null
        assert PersonHistory.count() == 1

        params.id = personRecord.id

        controller.delete()

        assert PersonHistory.count() == 0
        assert PersonHistory.get(personRecord.id) == null
        assert response.redirectedUrl == '/personRecord/list'
    }
}
