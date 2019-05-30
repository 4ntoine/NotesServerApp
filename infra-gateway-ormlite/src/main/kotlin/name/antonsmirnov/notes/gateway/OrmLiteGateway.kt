package name.antonsmirnov.notes.usecase

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import name.antonsmirnov.notes.domain.Note as NoteDomainEntity
import name.antonsmirnov.notes.gateway.Gateway
import name.antonsmirnov.notes.gateway.mapper.Mapper
import name.antonsmirnov.notes.gateway.Note as NoteDbEntity

/**
 * ORM Lite Gateway implementation
 */
class OrmLiteGateway(
    private val connectionSource: ConnectionSource,
    private val mapper: Mapper
) : Gateway {

    private val dao: Dao<NoteDbEntity, String> =
            DaoManager.createDao(connectionSource, NoteDbEntity::class.java)

    init {
        TableUtils.createTableIfNotExists(connectionSource, NoteDbEntity::class.java)
    }

    override fun add(note: NoteDomainEntity): String {
        val dbNote = mapper.map(note)
        dao.createIfNotExists(dbNote)
        return dbNote.id.toString() // TODO: doing some part of mapper work (smells).
    }

    override fun update(note: NoteDomainEntity) {
        dao.update(mapper.map(note))
    }

    override fun delete(id: String) {
        dao.deleteById(id)
    }

    override fun list(): Collection<NoteDomainEntity> = dao
            .queryForAll()
            .filter { it != null }
            .map { mapper.map(it) }
}