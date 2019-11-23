package name.antonsmirnov.notes.controller.rest

import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.Produces
import java.util.Random

import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import name.antonsmirnov.notes.domain.Note
import name.antonsmirnov.notes.gateway.Gateway

import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import io.quarkus.test.junit.QuarkusTest

@QuarkusTest
open class ListNotesControllerTest {
    companion object {
        private val random = Random()
        private val title = "title${random.nextInt()}"
        private val body = "body${random.nextInt()}"
    }

    @Inject
    private lateinit var controller: ListNotesController

    @Inject
    private lateinit var gateway: Gateway

    @BeforeEach
    fun setUp() {
        cleanDb()
    }

    @Transactional
    open fun cleanDb() = gateway.list().forEach { gateway.delete(it.id!!) }

    @Test
    @Transactional
    open fun testListNotes() {
        val expectedNote = addNote()

        val response = controller.list()

        assertNotNull(response)
        assertNotNull(response.notes)
        assertEquals(1, response.notes.size)

        val actualNote = response.notes.first()
        assertEquals(expectedNote.title, actualNote.title)
        assertEquals(expectedNote.body, actualNote.body)
        assertEquals(expectedNote.id, actualNote.id)
    }

    @Test
    open fun testListNotesEndpoint() {
        val extectedNote = addNote()

        given()
            .`when`().get("/api/list")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("notes[0].id", equalTo(extectedNote.id))
                .body("notes[0].title", equalTo(extectedNote.title))
                .body("notes[0].body", equalTo(extectedNote.body))
    }

    @Transactional
    open fun addNote(): Note {
        val expectedNote = Note(title, body)
        expectedNote.id = gateway.add(expectedNote)
        val actualNotes = gateway.list()

        assertEquals(1, actualNotes.size)
        val actualNote = actualNotes.first()
        assertEquals(expectedNote.id, actualNote.id)
        assertEquals(expectedNote.title, actualNote.title)
        assertEquals(expectedNote.body, actualNote.body)

        return expectedNote
    }
}