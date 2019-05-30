package name.antonsmirnov.notes.usecase

/**
 * "Add Note" use case interface
 */
interface AddNote {
    /**
     * Request model
     */
    data class Request(
        val title: String,
        val body: String?)

    /**
     * Response model
     */
    data class Response(val id: String)

    /**
     * Execute method ("Command" design pattern)
     */
    fun execute(request: Request): Response
}