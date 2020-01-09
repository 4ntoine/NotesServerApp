package name.antonsmirnov.notes.controller.ui

import name.antonsmirnov.notes.usecase.ListNotes
import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(ListNotesUIController::class)
class UIControllerErrorsTest {
    companion object {
        private val random = Random()
        private val errorMessage = "error${random.nextInt()}"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Configuration
    class UseCaseConfiguration {
        @Bean
        fun getListNotesController() = ListNotesUIController(object : ListNotes {
            override suspend fun execute(): ListNotes.Response {
                throw RuntimeException(errorMessage)
            }
        })
    }

    @Test
    fun testListNotes() {
        this.mockMvc
            .perform(get("/app/list"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(errorMessage)))
    }
}