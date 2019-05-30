package name.antonsmirnov.notes.gateway.generator

import java.util.*

/**
 * IdGenerator implementation that uses UUID
 */
class UuidIdGenerator : IdGenerator {
    override fun generate(): String = UUID.randomUUID().toString()
}