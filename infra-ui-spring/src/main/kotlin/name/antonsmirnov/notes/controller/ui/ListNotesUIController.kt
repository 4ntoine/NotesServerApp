package name.antonsmirnov.notes.controller.ui

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import name.antonsmirnov.notes.usecase.ListNotes

@Controller
class ListNotesUIController(private val useCase: ListNotes) {

    @RequestMapping("/app/list")
    fun list(model: Model): String {
        model.addAttribute("notes", useCase.execute().notes)
        return "listNotes"
    }
}