package name.antonsmirnov.notes.gateway

import javax.persistence.EntityManager

class DaoHelper {
    companion object {
        /**
         * @param entityManager EntityManager
         * @param table Table
         * @return List of entities from EntityManager of table
         */
        fun list(entityManager: EntityManager, table: String): Collection<*> {
            return entityManager.createQuery("SELECT e FROM $table e").resultList
        }
    }
}