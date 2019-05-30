package name.antonsmirnov.notes.controller

import name.antonsmirnov.notes.usecase.ListNotes
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListNotesRestController(private val useCase: ListNotes) {

    // no request model required

    data class NoteJson(
        val id: String,
        val title: String,
        val body: String?)

    /**
     * JSON dto for AddNote.Response model
     */
    data class ListNotesResponseJson(
        val notes: Collection<NoteJson>)

    @RequestMapping("/list")
    fun list(): ListNotesResponseJson
    {
        // execute interactor
        val response = useCase.execute()

        // map canonical dto back to JSON dto and return
        return ListNotesResponseJson(response.notes.map { NoteJson(it.id!!, it.title, it.body) })
    }
}