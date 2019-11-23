plugins {
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":app-impl"))
    implementation(project(":infra-gateway-ormlite"))
    implementation(project(":infra-rest-spring"))
    implementation(project(":infra-ui-spring"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(kotlin("reflect:${rootProject.extra["kotlinVersion"]}"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
