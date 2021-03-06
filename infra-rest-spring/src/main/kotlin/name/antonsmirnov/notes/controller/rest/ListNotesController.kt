package name.antonsmirnov.notes.controller.rest

import kotlinx.coroutines.runBlocking
import name.antonsmirnov.notes.usecase.ListNotes
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListNotesController(private val useCase: ListNotes) {

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

    @RequestMapping("/api/list")
    fun list(): ResponseJson = runBlocking {
        // execute interactor
        val response = useCase.execute()

        // map canonical dto back to JSON dto and return
        ResponseJson(response.notes.map { NoteJson(it.id!!, it.title, it.body) })
    }
}