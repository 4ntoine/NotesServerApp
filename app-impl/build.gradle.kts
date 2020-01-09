dependencies {
    api(project(":app-api"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${rootProject.extra["coroutinesTestVersion"]}")
}