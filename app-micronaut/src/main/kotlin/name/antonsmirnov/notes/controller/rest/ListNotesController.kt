package name.antonsmirnov.notes.controller.rest

import name.antonsmirnov.notes.usecase.ListNotes
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import javax.inject.Inject

@Controller
class ListNotesController {

    @Inject
    private lateinit var useCase: ListNotes

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

    @Get("/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun list(): ResponseJson
    {
        // execute interactor
        val response = useCase.execute()

        // map canonical dto back to JSON dto and return
        return ResponseJson(response.notes.map { NoteJson(it.id!!, it.title, it.body) })
    }
}