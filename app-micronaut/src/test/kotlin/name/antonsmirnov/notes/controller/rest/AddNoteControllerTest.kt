package name.antonsmirnov.notes.controller.rest

import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import name.antonsmirnov.notes.usecase.AddNote

object TestData {
    const val title = "title"
    const val body = "body"
}

@MicronautTest(packages=["name.antonsmirnov.notes"])
class AddNoteControllerTest(
    private val controller: AddNoteController
) : BehaviorSpec({

    given("AddNoteController") {
        `when`("title and body are passed") {
            val result = controller.add(TestData.title, TestData.body)
            then("the result is concatenated string of title and body") {
                result shouldBe AddNoteController.ResponseJson("${TestData.title}_${TestData.body}")
            }
        }
    }
}) {
    @MockBean(AddNote::class)
    fun buildAddNote(): AddNote {
        return object : AddNote {
            override fun execute(request: AddNote.Request): AddNote.Response
             = AddNote.Response("${request.title}_${request.body}")
        }
    }
}