package name.antonsmirnov.notes.domain

/**
 * Domain entity: Note
 */
class Note(
    var id: String?,
    var title: String,
    var body: String?) {

    /**
     * Convenience constructor
     */
    constructor(title: String, body: String?) : this(null, title, body)
}