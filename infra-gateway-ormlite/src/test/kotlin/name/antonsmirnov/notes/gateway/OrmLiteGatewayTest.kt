package name.antonsmirnov.notes.gateway

import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.support.ConnectionSource
import name.antonsmirnov.notes.domain.Note
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.OrmLiteGateway
import org.junit.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertTrue

class OrmLiteGatewayTest {
    private val mapper = MapperImpl()
    private lateinit var connection: ConnectionSource
    private lateinit var gateway: Gateway

    @BeforeTest
    fun before() {
        connection = JdbcConnectionSource("jdbc:h2:mem:notes")
        gateway = OrmLiteGateway(connection, mapper)
    }

    @AfterTest
    fun after() {
        connection.close()
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

    // TODO: implement another tests
}