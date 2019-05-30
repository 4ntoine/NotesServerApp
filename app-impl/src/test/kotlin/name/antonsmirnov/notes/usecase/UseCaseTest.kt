package name.antonsmirnov.notes.usecase

import name.antonsmirnov.notes.gateway.Gateway
import name.antonsmirnov.notes.gateway.InMemoryGateway
import name.antonsmirnov.notes.gateway.generator.IdGenerator
import name.antonsmirnov.notes.gateway.generator.UuidIdGenerator

abstract class UseCaseTest {
    protected var generator: IdGenerator = UuidIdGenerator()
    protected val gateway: Gateway = InMemoryGateway(generator)
}