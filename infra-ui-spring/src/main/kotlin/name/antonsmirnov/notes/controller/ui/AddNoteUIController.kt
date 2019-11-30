package name.antonsmirnov.notes.controller.ui

import name.antonsmirnov.notes.usecase.AddNote
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AddNoteUIController(private val useCase: AddNote) {

    @RequestMapping("/app/add", method = [RequestMethod.GET])
    fun show(): String {
        return "addNote"
    }

    @RequestMapping("/app/add", method = [RequestMethod.POST])
    fun add(@RequestParam title: String,
            @RequestParam body: String?,
            model: Model): String = tryCatching(model) {

        // map request params to canonical dto
        val request = AddNote.Request(title, body)

        // add with use case impl
        useCase.execute(request)

        // forward to list notes
        "redirect:/app/list"
    }
}