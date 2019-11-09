dependencies {
    api(project(":app-api"))
    implementation("org.springframework:spring-web:${rootProject.extra["springMvcVersion"]}")
}