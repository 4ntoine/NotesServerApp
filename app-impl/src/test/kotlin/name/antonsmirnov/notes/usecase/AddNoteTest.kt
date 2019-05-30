package name.antonsmirnov.notes.usecase

import org.junit.Test
import kotlin.test.assertTrue

class AddNoteTest : UseCaseTest() {
    private val useCase: AddNote = AddNoteImpl(gateway)

    private fun addNote(title: String, body: String?) {
        val request = AddNote.Request(title, body)
        val response = useCase.execute(request)
        assertTrue(response.id.isNotEmpty())
    }

    @Test
    fun testAddShouldReturnId() {
        // using id generator as just String generator
        val title = generator.generate()
        val body = generator.generate()
        addNote(title, body)
    }

    @Test
    fun testAddShouldAcceptNoteWithNullBody() {
        val title = generator.generate()
        val body: String? = null
        addNote(title, body)
    }
}