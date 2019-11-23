val ormliteVersion by extra { "4.45" }
val h2Version by extra { "1.4.199" }
val kotlinVersion by extra { "1.3.60" }
val springBootVersion by extra { "2.1.3.RELEASE" }
val springMvcVersion by extra { "5.1.6.RELEASE" }
val moduleVersion by extra { "1.3" }
val grpcVersion by extra { "1.21.0" }

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.60" apply false
    id("org.jetbrains.kotlin.multiplatform") version "1.3.60" apply false
    `java`
    `maven-publish`
    `idea`
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.60")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.3.60")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.3.RELEASE")
        classpath("net.ltgt.gradle:gradle-apt-plugin:0.7")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.8")
    }
}

fun isMultiplatformModule(moduleName: String) = (moduleName == "domain" || moduleName == "app-api")
fun isEndAppModule(moduleName: String) = (moduleName == "app-spring" || moduleName == "app-quarkus")

subprojects {

    if (!isMultiplatformModule(name)) {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    } else {
        apply(plugin = "org.jetbrains.kotlin.multiplatform")
        apply(plugin = "org.jetbrains.kotlin.native.cocoapods")
    }

    group = "name.antonsmirnov.notes"
    version = "${rootProject.extra["moduleVersion"]}"

    apply(plugin = "maven-publish")

    if (!isMultiplatformModule(name)) {
//        project.java {
//            sourceCompatibility = JavaVersion.VERSION_1_8
//            targetCompatibility = JavaVersion.VERSION_1_8
//        }

        // there is no need to publish end user app as it's not a module that can be used to build some other app
        if (!isEndAppModule(name)) {
            publishing {
                publications {
                    register("maven", MavenPublication::class) {
                        from(components["java"])
                    }
                }
            }
        }
    }

    repositories {
        jcenter()
        mavenCentral()
    }

    if (!isMultiplatformModule(name)) {
        dependencies {
            implementation(kotlin("stdlib-jdk8"))
            testImplementation(kotlin("test"))
            testImplementation(kotlin("test-junit"))
        }
    }
}
