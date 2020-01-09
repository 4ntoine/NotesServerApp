package name.antonsmirnov.notes.usecase

import name.antonsmirnov.notes.gateway.Gateway

/**
 * Default implementation of ListNotes use case
 */
class ListNotesImpl(private val gateway: Gateway) : ListNotes {
    override suspend fun execute(): ListNotes.Response {
        return ListNotes.Response(gateway
                .list()
                .map { ListNotes.Note(it.id!!, it.title, it.body) })
    }

}