package name.antonsmirnov.notes.usecase

import javax.persistence.EntityManager
import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.gateway.Gateway
import name.antonsmirnov.notes.gateway.mapper.Mapper
import java.util.*
import name.antonsmirnov.notes.gateway.Note as NoteDbEntity

/**
 * ORM Lite Gateway implementation
 */
class JpaGateway(
    private val entityManager: EntityManager,
    private val mapper: Mapper
) : Gateway {

    private fun callInTransaction(block: () -> Unit) {
        val ta = entityManager.getTransaction()
        ta.begin()
        try {
            block()
            ta.commit()
        } catch (e: Exception) {
            ta.rollback()
        }
    }

    override fun add(note: NoteDomainEntity): String {
        val dbNote = mapper.map(note)
        callInTransaction {
            entityManager.persist(dbNote)
            entityManager.flush()
        }
        return dbNote.id.toString() // TODO: doing some part of mapper work (smells).
    }

    override fun update(note: NoteDomainEntity) {
        entityManager.merge(mapper.map(note))
    }

    override fun delete(id: String) {
        val note = entityManager.find(NoteDbEntity::class.java, UUID.fromString(id)) ?: return
        callInTransaction {
            entityManager.remove(note)
            entityManager.flush()
        }
    }

    override fun list(): Collection<NoteDomainEntity> {
        val entities = entityManager
                .createQuery("SELECT n FROM Note n")
                .getResultList()

        return entities.map { mapper.map(it as NoteDbEntity) }
    }
}