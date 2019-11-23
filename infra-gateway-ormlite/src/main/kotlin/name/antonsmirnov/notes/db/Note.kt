package name.antonsmirnov.notes.db

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

@DatabaseTable(tableName = "Notes")
class Note(
    @DatabaseField(generatedId = true)
    var id: UUID? = null,
    // this is where decoupling of domain entity and db entity pays off:
    // we can use db UUID field with UUID generator

    @DatabaseField
    var title: String = "",

    @DatabaseField
    var body: String? = null)