package name.antonsmirnov.notes.db

import name.antonsmirnov.notes.db.Note.Companion.TABLE
import java.util.*
import javax.persistence.*

@Entity
@Table(name = TABLE)
class Note (
    @Id
    var id: UUID? = null,

    @Column(name = COLUMN_TITLE, nullable = false)
    var title: String = "",

    @Column(name = COLUMN_BODY, nullable = true)
    var body: String? = null)
{
    companion object {
        const val TABLE = "Note"
        const val COLUMN_TITLE = "title"
        const val COLUMN_BODY = "body"
    }
}


/*
@Entity
@Table(name = TABLE)
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var accountId: Long? = null
    @Column(name = COLUMN_EMAIL, unique = true, nullable = false)
    var email: String? = null
    @Column(name = COLUMN_PASSWORD_SALT, nullable = false)
    var passwordSalt: String? = null
    @Column(name = COLUMN_PASSWORD_HASH, nullable = false)
    var passwordHash: ByteArray? = null
    @Column(name = COLUMN_CONFIRMATION_EMAIL_SENT, nullable = true)
    var confirmationSent: Long = 0 // millis since Epoch
    @Column(name = COLUMN_CONFIRMATION_TOKEN, nullable = true)
    var confirmationToken: String? = null
    @Column(name = COLUMN_CONFIRMED, nullable = false)
    var confirmed: Boolean = false
    @Column(name = COLUMN_IS_ADMIN, nullable = false)
    var isAdmin: Boolean = false
    @Column(name = COLUMN_RESET_PASSWORD_EMAIL_SENT, nullable = true)
    var resetPasswordSent: Long = 0 // millis since Epoch
    @Column(name = COLUMN_RESET_PASSWORD_TOKEN, nullable = true)
    var resetPasswordToken: String? = null

    companion object {
        val TABLE = "Account"

        val COLUMN_EMAIL = "email"

        val COLUMN_PASSWORD_SALT = "passwordSalt"

        val COLUMN_PASSWORD_HASH = "passwordHash"

        val COLUMN_CONFIRMATION_EMAIL_SENT = "confirmationSent"

        val COLUMN_CONFIRMATION_TOKEN = "confirmationToken"

        val COLUMN_CONFIRMED = "confirmed"

        val COLUMN_IS_ADMIN = "isAdmin"

        val COLUMN_RESET_PASSWORD_EMAIL_SENT = "resetPasswordSent"

        val COLUMN_RESET_PASSWORD_TOKEN = "resetPasswordToken"
    }
}
*/