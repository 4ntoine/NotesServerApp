package name.antonsmirnov.notes

import com.j256.ormlite.jdbc.JdbcConnectionSource
import name.antonsmirnov.notes.gateway.OrmLiteGateway
import name.antonsmirnov.notes.gateway.mapper.MapperImpl
import name.antonsmirnov.notes.usecase.*
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class ServerApp

@Configuration
class Configuration {
    val connectionSource = JdbcConnectionSource("jdbc:h2:mem:notes") // in-memory db
    val mapper = MapperImpl()
    val gateway = OrmLiteGateway(connectionSource, mapper)
    val addNote: AddNote = AddNoteImpl(gateway)
    val listNotes: ListNotes = ListNotesImpl(gateway)

    @Bean
    fun getAddNoteUseCase(): AddNote = addNote

    @Bean
    fun getListNotesUseCase(): ListNotes = listNotes
}

fun main(args: Array<String>) {
    SpringApplication.run(ServerApp::class.java, *args)
}