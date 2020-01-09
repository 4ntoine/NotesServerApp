package name.antonsmirnov.notes.controller.rest

import kotlinx.coroutines.runBlocking
import name.antonsmirnov.notes.usecase.ListNotes
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/api/list") // Quarkus requires it to be controller annotations (not method annotations)
class ListNotesController {

    @Inject
    lateinit var useCase: ListNotes // Quarkus requires it to be fields (not ctor argument)

    // no request model required

    data class NoteJson(
        val id: String,
        val title: String,
        val body: String?)

    /**
     * JSON dto for AddNote.Response model
     */
    data class ResponseJson(
        val notes: Collection<NoteJson>)

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun list(): ResponseJson = runBlocking {
        // execute interactor
        val response = useCase.execute()

        // map canonical dto back to JSON dto and return
        ResponseJson(response.notes.map { NoteJson(it.id!!, it.title, it.body) })
    }
}