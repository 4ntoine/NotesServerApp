kotlin {
    jvm()
    iosX64() // iOS simulator

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(project(":domain"))
            }
        }
        val commonIos by creating
        getByName("iosX64Main").apply {
            dependsOn(commonIos)
        }
        getByName("jvmMain").apply {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }

    // All exceptions in Kotlin are not checked, but in Swift they are checked.
    // So we need @Throws annotation for iOS compatibility to generate swift signatures with `.. throws -> ..`
    // This requires @ExperimentalMultiplatform annotation in all methods with @Throws.
    // In order to prevent adding @ExperimentalMultiplatform every here and there we can use compiler option:
    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-Xuse-experimental=kotlin.ExperimentalMultiplatform"
            }
        }
    }

    cocoapods {
        summary = "App API of NotesServerApp"
        homepage = "https://github.com/4ntoine/NotesServerApp"
    }
}