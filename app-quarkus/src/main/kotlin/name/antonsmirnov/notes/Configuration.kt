package name.antonsmirnov.notes

import name.antonsmirnov.notes.gateway.JpaGateway
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.*
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
open class Configuration {

    @Produces
    open fun getJpaGateway(entityManager: EntityManager): JpaGateway {
        val mapper = MapperImpl()
        return JpaGateway(entityManager, mapper, false)
    }

    @Produces
    open fun getAddNoteUseCase(gateway: JpaGateway) = AddNoteImpl(gateway)

    @Produces
    open fun getListNotesUseCase(gateway: JpaGateway): ListNotes = ListNotesImpl(gateway)
}