package name.antonsmirnov.notes.controller.ui

import org.springframework.ui.Model

/**
 * Show error in UI if thrown
 * @param model Model
 * @param block lambda with errors handling
 * @return template name according to Thymeleaf agreement
 */
fun tryCatching(model: Model, block: () -> String): String {
    try {
        return block()
    } catch (e: Exception) {
        model.addAttribute("error", e)
        return "error"
    }
}