package name.antonsmirnov.notes.usecase

import name.antonsmirnov.notes.domain.Note
import name.antonsmirnov.notes.gateway.Gateway

/**
 * Default implementation of AddNote use case
 */
class AddNoteImpl(private val gateway: Gateway) : AddNote {
    override suspend fun execute(request: AddNote.Request): AddNote.Response {
        val note = Note(request.title, request.body)
        val id = gateway.add(note)
        return AddNote.Response(id)
    }
}