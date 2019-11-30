package name.antonsmirnov.notes.ui

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import name.antonsmirnov.notes.usecase.AddNote
import java.util.*

@Route("app/add")
class AddNoteUIController(private val useCase: AddNote) : VerticalLayout() {
    init {
        add(H2("Add note"))

        val titleField = TextField("Title").apply {
            isRequired = true
        }

        val bodyField = TextArea("Body").apply {
            isRequired = false
        }
        add(titleField, bodyField)

        val cancelButton = Button("Cancel") {
            navigateToList(ui)
        }
        val addButton = Button("Add note") {
            try {
                val response = useCase.execute(AddNote.Request(titleField.value, bodyField.value))
                Notification.show("Note with id=${response.id} added")
                navigateToList(ui)
            } catch (e: Exception) {
                Notification.show("Failed to add the note!")
            }
        }
        add(HorizontalLayout(cancelButton, addButton))
    }

    private fun navigateToList(ui: Optional<UI>) = ui.ifPresent {
        it.navigate(ListNotesUIController::class.java)
    }
}