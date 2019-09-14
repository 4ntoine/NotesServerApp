package name.antonsmirnov.notes.gateway

import name.antonsmirnov.notes.domain.Note
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.JpaGateway
import org.junit.Test
import javax.persistence.EntityManager
import javax.persistence.Persistence
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OrmLiteGatewayTest {
    private val mapper = MapperImpl()
    private lateinit var entityManager: EntityManager
    private lateinit var gateway: Gateway


    companion object {
        const val PERSISTENCE_UNIT_NAME = "NotesTestPersistenceUnit"
    }

    @BeforeTest
    fun before() {
        val entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
        entityManager = entityManagerFactory.createEntityManager()
        gateway = JpaGateway(entityManager, mapper)
    }

    @AfterTest
    fun after() {
        entityManager.close()
    }

    @Test
    fun testAddReturnsId() {
        val note = Note("title", "body")
        val id = gateway.add(note)
        assertTrue(id.isNotEmpty())
    }

    @Test
    fun testAddedNoteIsListed() {
        val note = Note("title", "body")
        val id = gateway.add(note)
        val notes = gateway.list()
        assertTrue(notes.isNotEmpty())
        assertTrue(notes.any { it.id == id })
    }

    @Test
    fun testDeleteActuallyDeletes() {
        val note = Note("title", "body")
        val id = gateway.add(note)
        assertTrue(id.isNotEmpty())
        val listBefore = gateway.list()
        assertEquals(1, listBefore.size)
        gateway.delete(id)
        val listAfter = gateway.list()
        assertEquals(0, listAfter.size)
    }
}