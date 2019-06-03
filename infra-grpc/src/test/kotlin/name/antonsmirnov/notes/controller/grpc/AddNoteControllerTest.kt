package name.antonsmirnov.notes.controller.grpc

import com.google.protobuf.StringValue
import name.antonsmirnov.notes.usecase.AddNote
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class AddNoteControllerTest : BaseControllerTest() {
    companion object {
        private const val title = "title"
        private const val body = "body"
    }

    override fun getController() = AddNoteController(object : AddNote {
        override fun execute(request: AddNote.Request): AddNote.Response =
                AddNote.Response("${request.title}_${request.body}")
    })

    private lateinit var controllerStub: AddNoteControllerGrpc.AddNoteControllerBlockingStub

    @BeforeTest
    override fun before() {
        super.before()
        controllerStub = AddNoteControllerGrpc.newBlockingStub(channel)
    }

    @Test
    fun testAddNote() {
        val requestGrpc = AddNoteRequest
                .newBuilder()
                .setTitle(title)
                .setBody(StringValue
                            .newBuilder()
                            .setValue(body)
                            .build())
                .build()
        val responseGrpc = controllerStub.add(requestGrpc)

        assertEquals("${requestGrpc.title}_${requestGrpc.body.value}", responseGrpc.id)
    }
}