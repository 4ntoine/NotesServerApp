dependencies {
    api(project(":app-api"))

    implementation("org.springframework:spring-webmvc:${rootProject.extra["springMvcVersion"]}")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.2.0.RELEASE")
}