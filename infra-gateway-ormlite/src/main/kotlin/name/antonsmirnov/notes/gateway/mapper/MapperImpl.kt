package name.antonsmirnov.notes.gateway.mapper

import java.util.*
import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.gateway.Note as NoteDbEntity

/**
 * Default implementation of Mapper
 */
class MapperImpl : Mapper {
    override fun map(from: NoteDomainEntity): NoteDbEntity =
            NoteDbEntity(if (from.id != null) UUID.fromString(from.id) else null, from.title, from.body)

    override fun map(from: NoteDbEntity): NoteDomainEntity =
            NoteDomainEntity(from.id.toString(), from.title, from.body)
}