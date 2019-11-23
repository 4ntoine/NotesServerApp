plugins {
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.60"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api(project(":app-api"))

    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final")
    testImplementation("com.h2database:h2:1.4.197")
    testImplementation("org.hibernate:hibernate-core:5.4.8.Final")
}