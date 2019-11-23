package name.antonsmirnov.notes.controller.rest

import javax.inject.Inject
import javax.ws.rs.Produces
import java.util.Random

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import io.quarkus.test.junit.QuarkusTest

import name.antonsmirnov.notes.usecase.AddNote
import name.antonsmirnov.notes.usecase.AddNote.Request
import name.antonsmirnov.notes.gateway.Gateway

@QuarkusTest
public class AddNoteControllerTest {
    companion object {
        private val random = Random()
        private val title = "title${random.nextInt()}"
        private val body = "body${random.nextInt()}"
    }

    @Inject 
    private lateinit var controller: AddNoteController

    @Inject
    private lateinit var gateway: Gateway

    @Test
    fun testAdd() {
        val response = controller.add(title, body)

        assertNotNull(response)
        assertNotNull(response.id)

        val notes = gateway.list()
        assertNotNull(notes)
        assertEquals(1, notes.size)
        val note = notes.first()
        assertEquals(title, note.title)
        assertEquals(body, note.body)
        assertEquals(response.id, note.id)
    }

    @Test
    fun testAddEndpoint() {
        given()
            .params(mapOf("title" to "title1", "body" to "body1"))
            .`when`().get("/api/add")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", containsString("-"))
    }
}