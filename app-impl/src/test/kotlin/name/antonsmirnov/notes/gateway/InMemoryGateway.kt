package name.antonsmirnov.notes.gateway

import name.antonsmirnov.notes.domain.Note
import name.antonsmirnov.notes.gateway.generator.IdGenerator

/**
 * In-memory implementation of Gateway for testing
 */
class InMemoryGateway(private val generator: IdGenerator) : Gateway {

    private val notes = mutableMapOf<String, Note>()

    override fun add(note: Note): String {
        // TODO: check if already added as an improvement
        val id = generator.generate()
        note.id = id
        notes[id] = note
        return id
    }

    override fun update(note: Note) {
        // nothing (instance fields already updated)
    }

    override fun delete(id: String) {
        notes.remove(id)
    }

    override fun list(): Collection<Note> {
        return notes.values
    }
}