package name.antonsmirnov.notes.controller.rest

import name.antonsmirnov.notes.usecase.AddNote
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.transaction.Transactional

@Path("/api/add")  // Quarkus requires it to be controller annotations (not method annotations)
class AddNoteController {

    @Inject
    lateinit var useCase: AddNote // Quarkus requires it to be fields (not ctor argument)

    /**
     * JSON dto for AddNote.Response model
     */
    data class ResponseJson(
        val id: String)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun add(@QueryParam("title") title: String,
            @QueryParam("body") body: String?): ResponseJson {
        // map JSON dto to canonical dto
        val request = AddNote.Request(title, body)

        // execute interactor
        val response = useCase.execute(request)

        // map canonical dto back to JSON dto and return
        return ResponseJson(response.id)
    }
}