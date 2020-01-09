package name.antonsmirnov.notes.ui

import kotlinx.coroutines.runBlocking
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import name.antonsmirnov.notes.usecase.ListNotes

@Route("app/list")
class ListNotesUIController(private val useCase: ListNotes) : VerticalLayout() {
    init {
        runBlocking {
            try {
                val notes = useCase.execute().notes
                add(H2("Notes: ${notes.size}"))

                val grid = Grid(ListNotes.Note::class.java)
                grid.setColumns("id", "title", "body")
                grid.columns.forEach { it.setAutoWidth(true) }
                grid.setItems(notes)
                add(grid)

                val addButton = Button("Add note") {
                    ui.ifPresent {
                        it.navigate(AddNoteUIController::class.java)
                    }
                }
                add(addButton)
            } catch (e: Exception) {
                Notification.show("Failed to list the notes")
            }
        }
    }
}