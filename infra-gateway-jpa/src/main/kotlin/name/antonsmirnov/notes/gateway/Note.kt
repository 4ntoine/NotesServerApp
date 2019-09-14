package name.antonsmirnov.notes.gateway

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Notes")
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null
    // this is where decoupling of domain entity and db entity pays off:
    // we can use db UUID field with UUID generator

    @Column(name = "title")
    var title: String = ""

    @Column(name = "body", nullable = true)
    var body: String? = null
}