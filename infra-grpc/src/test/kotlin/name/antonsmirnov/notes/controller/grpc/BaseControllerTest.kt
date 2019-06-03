package name.antonsmirnov.notes.controller.grpc

import io.grpc.BindableService
import io.grpc.ManagedChannelBuilder
import io.grpc.ServerBuilder
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseControllerTest {
    companion object {
        private const val port = 8081
    }

    protected abstract fun getController(): BindableService

    protected val server = ServerBuilder
            .forPort(port)
            .addService(getController())
            .build()

    protected val channel = ManagedChannelBuilder
            .forAddress("127.0.0.1", port)
            .usePlaintext()
            .build()

    @BeforeTest
    open fun before() {
        server.start()
    }

    @AfterTest
    open fun after() {
        server.shutdownNow()
    }

}