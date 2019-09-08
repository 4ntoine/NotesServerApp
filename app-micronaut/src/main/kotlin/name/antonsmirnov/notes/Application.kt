package name.antonsmirnov.notes

import io.micronaut.runtime.Micronaut
import com.j256.ormlite.jdbc.JdbcConnectionSource
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.*
import javax.inject.*
import io.micronaut.context.annotation.*

@Factory
class Configuration {
    val connectionSource = JdbcConnectionSource("jdbc:h2:mem:notes") // in-memory db
    val mapper = MapperImpl()
    val gateway = OrmLiteGateway(connectionSource, mapper)
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