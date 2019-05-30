package name.antonsmirnov.notes.gateway.mapper

import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.gateway.Note as NoteDbEntity

/**
 * Maps domain entity to domain entity and vice versa
 * (some folks want to be pragmatic, but i want to be clean and separate domain entities and db entities)
 */
interface Mapper {
    fun map(from: NoteDomainEntity): NoteDbEntity
    fun map(from: NoteDbEntity): NoteDomainEntity
}