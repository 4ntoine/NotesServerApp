The `app` module just brings all the things together into one single jar file using Spring boot.
Using in-memory H2 database here.
Using REST controllers here (one can also use gRPC controllers instead).
Also find the tests for REST controllers with mocked use cases.

Clean architecture allows us to switch to another database or controller easily.