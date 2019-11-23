package name.antonsmirnov.notes.gateway

import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.db.Note as NoteDbEntity
import name.antonsmirnov.notes.db.Note.Companion.TABLE
import name.antonsmirnov.notes.gateway.mapper.Mapper
import java.lang.Exception
import java.util.*
import javax.persistence.EntityManager

class JpaGateway(
    private val entityManager: EntityManager,
    private val mapper: Mapper,
    private val resourceLocalTransactions: Boolean) : Gateway {

    private fun <T> inTransaction(block: () -> T): T {
        if (resourceLocalTransactions) {
            val transaction = entityManager.transaction
            transaction.begin()
            try {
                val result = block()
                entityManager.flush()
                transaction.commit()
                return result
            } catch (e: Exception) {
                transaction.rollback()
                throw e
            }
        } else {
            return block()
        }
    }

    override fun add(note: NoteDomainEntity): String = inTransaction {
        val dbNote = mapper.map(note)
        dbNote.id = UUID.randomUUID() // code generator (not all ORMs have UUID generators)
        entityManager.persist(dbNote)
        dbNote.id.toString()
    }

    override fun update(note: NoteDomainEntity): Unit = inTransaction {
        val dbNote = mapper.map(note)
        entityManager.merge(dbNote)
    }

    override fun delete(id: String) = inTransaction {
        val dbNote: NoteDbEntity = entityManager.find(
            NoteDbEntity::class.java, UUID.fromString(id)) as NoteDbEntity
        entityManager.remove(dbNote)
    }

    override fun list(): Collection<NoteDomainEntity> = DaoHelper
            .list(entityManager, TABLE)
            .map { mapper.map(it as NoteDbEntity) }
}