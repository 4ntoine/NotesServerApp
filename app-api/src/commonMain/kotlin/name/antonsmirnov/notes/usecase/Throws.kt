package name.antonsmirnov.notes.usecase

import kotlin.reflect.KClass

@ExperimentalMultiplatform
@OptionalExpectation
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
expect annotation class Throws(vararg val exceptionClasses: KClass<out Throwable>)