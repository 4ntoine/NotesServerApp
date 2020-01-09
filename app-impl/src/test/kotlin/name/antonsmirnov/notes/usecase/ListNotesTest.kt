package name.antonsmirnov.notes.usecase

import kotlinx.coroutines.test.runBlockingTest
import name.antonsmirnov.notes.domain.Note
import org.junit.Test
import kotlin.test.assertTrue

class ListNotesTest : UseCaseTest() {
    private val useCase: ListNotes = ListNotesImpl(gateway)

    @Test
    fun testAddAndList() = runBlockingTest {
        // using id generator as just String generator
        val note = Note(generator.generate(), generator.generate(), generator.generate())
        gateway.add(note)

        val response = useCase.execute()
        assertTrue(response.notes.isNotEmpty())
        assertTrue(response.notes.any {
            it.id == note.id &&
            it.title == note.title &&
            it.body == note.body
        })
    }
}