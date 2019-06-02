package name.antonsmirnov.notes.controller

import name.antonsmirnov.notes.usecase.ListNotes
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
class ListNotesRestControllerTest {
    companion object {
        private const val id = "id"
        private const val title = "title"
        private const val body = "body"
    }

    @Autowired
    private lateinit var controller: ListNotesRestController

    @Configuration
    class UseCaseConfiguration {
        @Bean
        fun getListNotesController() = ListNotesRestController(object : ListNotes {
            override fun execute(): ListNotes.Response =
                    ListNotes.Response(listOf(ListNotes.Note(id, title, body)))
        })
    }

    @Test
    fun testListNotes() {
        val response = controller.list()
        assertEquals(1, response.notes.size)

        val note = response.notes.first()
        assertEquals(id, note.id)
        assertEquals(title, note.title)
        assertEquals(body, note.body)
    }
}