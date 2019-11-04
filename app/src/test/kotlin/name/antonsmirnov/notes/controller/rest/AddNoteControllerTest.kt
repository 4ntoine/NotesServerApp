package name.antonsmirnov.notes.controller.rest

import name.antonsmirnov.notes.usecase.AddNote
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit4.SpringRunner
import java.util.Random
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class AddNoteControllerTest {
    companion object {
        private val random = Random()
        private val title = "title${random.nextInt()}"
        private val body = "body${random.nextInt()}"
    }

    @Autowired
    private lateinit var controller: AddNoteController

    @Configuration
    class UseCaseConfiguration {
        @Bean
        fun getAddNoteController() = AddNoteController(object : AddNote {
            override fun execute(request: AddNote.Request): AddNote.Response =
                    AddNote.Response("${request.title}_${request.body}")
        })
    }

    @Test
    fun testAddNote() {
        val request = AddNoteController.RequestJson(title, body)
        val response = controller.add(request)

        assertEquals("${request.title}_${request.body}", response.id)
    }
}