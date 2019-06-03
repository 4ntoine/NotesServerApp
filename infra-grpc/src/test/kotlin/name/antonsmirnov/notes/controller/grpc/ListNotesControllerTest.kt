package name.antonsmirnov.notes.controller.grpc

import name.antonsmirnov.notes.usecase.ListNotes
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class ListNotesControllerTest : BaseControllerTest() {
    companion object {
        private const val id = "id"
        private const val title = "title"
        private const val body = "body"
    }

    override fun getController() = ListNotesController(object : ListNotes {
        override fun execute(): ListNotes.Response =
                ListNotes.Response(listOf(ListNotes.Note(id, title, body)))
    })

    private lateinit var controllerStub: ListNotesControllerGrpc.ListNotesControllerBlockingStub

    @BeforeTest
    override fun before() {
        super.before()
        controllerStub = ListNotesControllerGrpc.newBlockingStub(channel)
    }

    @Test
    fun testListNotes() {
        val responseGrpc = controllerStub.list(ListNotesRequest.newBuilder().build())
        assertEquals(1, responseGrpc.noteCount)

        val note = responseGrpc.getNote(0)
        assertEquals(id, note.id)
        assertEquals(title, note.title)
        assertEquals(body, note.body.value)
    }
}