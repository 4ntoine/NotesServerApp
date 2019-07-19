package name.antonsmirnov.notes.usecase

/**
 * "List notes" use case interface
 */
interface ListNotes {

    // no request model required

    data class Note(
        val id: String?,
        val title: String,
        val body: String?)

    /**
     * Response model
     */
    data class Response(val notes: Collection<Note>)

    /**
     * Execute method ("Command" design pattern)
     */
    @Throws
    fun execute(): Response
}