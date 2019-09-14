package name.antonsmirnov.notes

import io.micronaut.runtime.Micronaut
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.*
import javax.inject.*
import io.micronaut.context.annotation.*
import javax.persistence.Persistence

@Factory
class Configuration {
    val entityManagerFactory = Persistence.createEntityManagerFactory("NotesProductionPersistenceUnit")
    val mapper = MapperImpl()
    val gateway = JpaGateway(entityManagerFactory.createEntityManager(), mapper)
    val addNote: AddNote = AddNoteImpl(gateway)
    val listNotes: ListNotes = ListNotesImpl(gateway)

    @Singleton
    @Bean
    fun getAddNoteUseCase(): AddNote = addNote

    @Singleton
    @Bean
    fun getListNotesUseCase(): ListNotes = listNotes
}

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("name.antonsmirnov.notes")
                .mainClass(Application.javaClass)
                .start()
    }
}