The `app-quarkus` module brings all the things together using Quarkus framework.
It brings few benefits such as small start-up time and less memory consumption due to AOT compilation.
Compiled with `-Pnative` it uses GraalVM native-images and can bring even more benefits which can
be useful for microservices and serverless applications.

Using Hibernate as JPA, in-memory H2 database (to be replaced for native image) and JTA transactions here.
Using REST controllers here exposed with Quarkus (declared with JAX-RS).
Also find the tests for REST controllers.
Using Maven as build too here due to not enough mature Gradle support by Quarkus.

Clean architecture allows us to be flexible on implementation details.