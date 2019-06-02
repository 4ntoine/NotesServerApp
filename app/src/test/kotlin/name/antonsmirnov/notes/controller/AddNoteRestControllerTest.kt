package name.antonsmirnov.notes.controller

import name.antonsmirnov.notes.usecase.AddNote
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class AddNoteRestControllerTest {
    companion object {
        private const val title = "title"
        private const val body = "body"
    }

    @Autowired
    private lateinit var controller: AddNoteRestController

    @Configuration
    class UseCaseConfiguration {
        @Bean
        fun getAddNoteController() = AddNoteRestController(object : AddNote {
            override fun execute(request: AddNote.Request): AddNote.Response =
                    AddNote.Response("${request.title}_${request.body}")
        })
    }

    @Test
    fun testAddNote() {
        val request = AddNoteRestController.AddNoteRequestJson(title, body)
        val response = controller.add(request)

        assertEquals("${request.title}_${request.body}", response.id)
    }
}