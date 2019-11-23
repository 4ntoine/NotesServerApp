import io.quarkus.gradle.tasks.QuarkusDev

plugins {
    id("io.quarkus")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":app-impl"))
    implementation(project(":infra-gateway-jpa"))
    implementation(project(":infra-rest-jax-rs")) {
        // provided by Quarkus or it's transitive dependencies
        exclude("javax.ws.rs:javax.ws.rs-api:2.0")
        exclude("javax.enterprise:cdi-api:2.0.SP1")
    }

    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-jsonb")
    implementation("io.quarkus:quarkus-jdbc-h2")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:1.0.0.CR2"))
    implementation("io.quarkus:quarkus-resteasy")
}

fun createMonitoringDirs(dirPath: String) {
    val dir = File(dirPath)
    if (!dir.exists()) {
        println("Creating $dirPath ...")
        if (!dir.mkdirs()) {
            println("ERROR: Failed to create $dirPath")
        }
    }
}

// fix for missing java dirs for Quarkus for monitoring
fun createKotlinMonitoringDirs() = createMonitoringDirs("$projectDir/build/classes/kotlin/main")

fun createJavaModuleMonitoringDirs(modulePath: String) {
    createMonitoringDirs("$modulePath/src/main/java")
    createMonitoringDirs("$modulePath/build/classes/java/main")
}

fun createJavaMonitoringDirs() {
    createJavaModuleMonitoringDirs("$projectDir/../app-impl")
    createJavaModuleMonitoringDirs("$projectDir/../infra-rest-jax-rs")
    createJavaModuleMonitoringDirs("$projectDir/../infra-gateway-jpa")
}

fun createMonitoringDirs() {
//    createKotlinMonitoringDirs() // commented as we create Hello1Controller, so the dir will be created
    createJavaMonitoringDirs()
}

tasks {
    // to create monitoring dirs after "./gradlew clean" is done
    getByName<Delete>("clean") {
        doLast { createMonitoringDirs() }
    }

    // to create monitoring dirs before "./gradlew quarkusDev" is done
    getByName<QuarkusDev>("quarkusDev") {
        doFirst { createMonitoringDirs() }
    }
}

createMonitoringDirs()

quarkus {
    createMonitoringDirs()
    setSourceDir("$projectDir/src/main/kotlin")
    setOutputDirectory("$projectDir/build/classes/kotlin/main")
}