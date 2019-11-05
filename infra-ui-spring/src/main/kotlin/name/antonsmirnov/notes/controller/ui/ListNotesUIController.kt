package name.antonsmirnov.notes.controller.ui

import name.antonsmirnov.notes.usecase.ListNotes
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ListNotesUIController(private val useCase: ListNotes) {

    @RequestMapping("/app/list")
    fun list(model: Model): String = tryCatching(model) {
        // no request is needed

        // add with use case impl
        val response = useCase.execute()

        // pass to view
        model.addAttribute("notes", response.notes)
        "listNotes"
    }
}