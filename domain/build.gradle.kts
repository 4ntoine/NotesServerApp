kotlin {
    jvm()
    iosX64() // iOS simulator

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        getByName("jvmMain").apply {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }

    cocoapods {
        summary = "Domain objects of NotesServerApp"
        homepage = "https://github.com/4ntoine/NotesServerApp"
    }
}