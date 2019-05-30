package name.antonsmirnov.notes.gateway

import name.antonsmirnov.notes.domain.Note

/**
 * Gateway interface (usually called "Repository" design pattern)
 */
interface Gateway {
    /**
     * Add note
     * @return id of added note.
     *         WARNING: note `id` field of `note` object will not to be updated!
     */
    fun add(note: Note): String

    /**
     * Update note
     */
    fun update(note: Note)

    /**
     * Delete note
     */
    fun delete(id: String)

    /**
     * List all notes
     * @return collection of notes
     */
    fun list(): Collection<Note>
}