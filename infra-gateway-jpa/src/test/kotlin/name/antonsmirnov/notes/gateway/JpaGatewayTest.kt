package name.antonsmirnov.notes.gateway

import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import javax.persistence.Persistence
import name.antonsmirnov.notes.gateway.Gateway
import name.antonsmirnov.notes.gateway.mapper.Mapper
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JpaGatewayTest {
    private val mapper: Mapper = MapperImpl()
    private lateinit var gateway: Gateway
    private val random = Random()

    @Before
    fun setUp() {
        val emf = Persistence.createEntityManagerFactory("NotesPersistenceUnit")
        val em = emf.createEntityManager()
        gateway = JpaGateway(em, mapper, true)
    }

    private fun randomTitle() = "title${random.nextInt()}"
    private fun randomBody() = "body${random.nextInt()}"

    @Test
    fun testAdd() {
        val title = randomTitle()
        val body = randomBody()
        val note = NoteDomainEntity(null, title, body)
        note.id = gateway.add(note)
        assertNotNull(note.id)

        val notes = gateway.list()
        assertNotNull(notes)
        assertEquals(1, notes.size)
        assertEquals(note.id, notes.first().id)
    }

    @Test
    fun testUpdate() {
        val title = randomTitle()
        val body = randomBody()
        val note = NoteDomainEntity(null, title, body)
        note.id = gateway.add(note)
        assertNotNull(note.id)

        val notes = gateway.list()
        assertNotNull(notes)
        assertEquals(1, notes.size)
        val actualNote = notes.first()
        assertEquals(note.id, actualNote.id)
        assertEquals(title, actualNote.title)
        assertEquals(body, actualNote.body)

        val newBody = randomBody()
        note.body = newBody
        gateway.update(note)

        val notesAfterUpdate = gateway.list()
        assertNotNull(notesAfterUpdate)
        assertEquals(1, notesAfterUpdate.size)
        val actualNoteAfterUpdate = notesAfterUpdate.first()
        assertEquals(note.id, actualNoteAfterUpdate.id)
        assertEquals(title, actualNoteAfterUpdate.title)
        assertEquals(newBody, actualNoteAfterUpdate.body)
    }

    @Test
    fun testDelete() {
        val title = randomTitle()
        val body = randomBody()
        val note = NoteDomainEntity(null, title, body)
        note.id = gateway.add(note)
        assertNotNull(note.id)

        val notes = gateway.list()
        assertNotNull(notes)
        assertEquals(1, notes.size)
        assertEquals(note.id, notes.first().id)

        gateway.delete(note.id.toString())
        val notesAfterDelete = gateway.list()
        assertNotNull(notesAfterDelete)
        assertEquals(0, notesAfterDelete.size)
    }
}