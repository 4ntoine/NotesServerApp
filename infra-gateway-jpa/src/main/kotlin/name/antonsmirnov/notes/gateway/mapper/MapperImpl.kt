package name.antonsmirnov.notes.gateway.mapper

import java.util.*
import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.gateway.Note as NoteDbEntity

/**
 * Default implementation of Mapper
 */
class MapperImpl : Mapper {
    override fun map(from: NoteDomainEntity): NoteDbEntity {
        val dbNote = NoteDbEntity()
        dbNote.id = if (from.id != null) UUID.fromString(from.id) else null
        dbNote.title = from.title
        dbNote.body = from.body
        return dbNote
    }

    override fun map(from: NoteDbEntity): NoteDomainEntity =
            NoteDomainEntity(from.id.toString(), from.title, from.body)
}