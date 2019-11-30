rootProject.name = "NotesServerApp"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "io.quarkus") {
                useModule("io.quarkus:quarkus-gradle-plugin:1.0.0.CR2")
            }
        }
    }
}

include("domain")
include("app-api")
include("app-impl")
include("infra-gateway-ormlite")
include("infra-gateway-jpa")
include("infra-rest-spring")
include("infra-rest-jax-rs")
include("infra-ui-spring")
include("infra-grpc")
include("app-spring")
//include("app-quarkus")
//include("app-spring-vaadin")