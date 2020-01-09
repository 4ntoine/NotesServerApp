dependencies {
    api(project(":app-api"))
    implementation("org.springframework:spring-web:${rootProject.extra["springMvcVersion"]}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${rootProject.extra["coroutinesTestVersion"]}")
}