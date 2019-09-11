package name.antonsmirnov.notes.controller.rest

import name.antonsmirnov.notes.usecase.AddNote
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import javax.inject.Inject

@Controller
open class AddNoteController {

    @Inject
    private lateinit var useCase: AddNote

    // no request model required

    /**
     * JSON dto for AddNote.Response model
     */
    data class ResponseJson(
        val id: String)

    @Get("/add")
    @Produces(MediaType.APPLICATION_JSON)
    fun add(title: String, body: String?): ResponseJson {
        // map JSON dto to canonical dto
        val request = AddNote.Request(title, body)

        // execute interactor
        val response = useCase.execute(request)

        // map canonical dto back to JSON dto and return
        return ResponseJson(response.id)
    }
}