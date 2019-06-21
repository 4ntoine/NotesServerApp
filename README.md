[![Build Status](https://travis-ci.org/4ntoine/NotesServerApp.svg?branch=master)](https://travis-ci.org/4ntoine/NotesServerApp)

# The project

This is a server-side JVM application for simple notes keeping (you can add and list notes via REST and gRPC) implemented in [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) in Kotlin.

# The motivation

Here is a quick recap of the benefits that clean architecture brings:

* Independent of Frameworks
* Testable
* Independent of UI
* Independent of Database
* Independent of any external agency

You can find few more README.md files in subdirectories.

# Clean architecture implementation

Find the classes in clean architecture diagram:

![Clean architecture diagram](https://github.com/4ntoine/NotesServerApp/blob/master/images/my_clean_arch.png?raw=true)

![Sequence diagram](https://github.com/4ntoine/NotesServerApp/blob/master/images/sequence.png?raw=true)

# Building

	./gradlew :app:bootJar

# Testing

## Unit testing

One can find few unit tests that demonstrate some benefits of clean architecture for testing.

	./gradlew test

## Manual testing

First, run the app from IDE or command line:

	./gradlew :app:bootRun

Second, add a note by navigating to URL as follows `http://localhost:8080/add?title=title1&body=body1`:

![Add a note](https://github.com/4ntoine/NotesServerApp/blob/master/images/add_note.png?raw=true)

Third, list the notes by navigating to URL `http://localhost:8080/list`

![List the notes](https://github.com/4ntoine/NotesServerApp/blob/master/images/list_notes.png?raw=true)

# Frameworks and tools

* [Kotlin](https://kotlinlang.org/) programming language
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE for coding
* [Gradle](https://gradle.org/) with Groovy DSL for building
* [ORMLite](http://ormlite.com/) ORM for persistence
* [H2](https://www.h2database.com/html/main.html) db engine for testing
* [Spring Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) for HTTP REST
* [Spring Boot](https://spring.io/projects/spring-boot) for configuration
* [Google Protocol Buffers](https://developers.google.com/protocol-buffers) and [gRPC](https://grpc.io/) for RPC
* [Travis CI](https://travis-ci.org/) for CI

# Feedback

Any feedback and discussion is appreciated.
Contact me on e-mail for this or fork the repository and pull a request.

# Author

Anton Smirnov

dev [at] antonsmirnov [dot] name

2019