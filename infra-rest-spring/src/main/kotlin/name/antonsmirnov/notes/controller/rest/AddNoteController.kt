package name.antonsmirnov.notes.controller.rest

import name.antonsmirnov.notes.usecase.AddNote
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AddNoteController(private val useCase: AddNote) {

    /**
     * JSON dto for AddNote.Request
     */
    data class RequestJson(
        val title: String,
        val body: String?)

    /**
     * JSON dto for AddNote.Response model
     */
    data class ResponseJson(
        val id: String)

    @RequestMapping("/add")
    fun add(requestJson: RequestJson): ResponseJson {
        // map JSON dto to canonical dto
        val request = AddNote.Request(requestJson.title, requestJson.body)

        // execute interactor
        val response = useCase.execute(request)

        // map canonical dto back to JSON dto and return
        return ResponseJson(response.id)
    }
}