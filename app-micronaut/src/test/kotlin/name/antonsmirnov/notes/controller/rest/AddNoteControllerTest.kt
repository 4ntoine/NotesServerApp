package name.antonsmirnov.notes.controller.rest

import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import name.antonsmirnov.notes.usecase.AddNote
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals

@MicronautTest(packages = ["name.antonsmirnov.notes"])
class AddNoteControllerTest {

    companion object {
        private const val title = "title"
        private const val body = "body"
    }

    @Inject // not injected
    private lateinit var controller: AddNoteController

    @MockBean(AddNote::class)
    fun buildAddNoteUseCase(): AddNote {
        return object : AddNote {
            override fun execute(request: AddNote.Request): AddNote.Response {
                return AddNote.Response("${request.title}_${request.body}")
            }
        }
    }

    @MockBean(AddNoteController::class)
    fun buildAddNoteController(): AddNoteController {
        return AddNoteController()
    }

    @Test
    fun testAddNote() {
        val response = controller.add(title, body) // in `null` (not initialized)
        assertEquals("${title}_ ${body}", response.id)
    }
}