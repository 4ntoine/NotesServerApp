dependencies {
    api(project(":app-api"))

    implementation("com.h2database:h2:${rootProject.extra["h2Version"]}")

    // `api` dependency as `JdbcConnectionSource` have to be reachable in the app for configuration
    api("com.j256.ormlite:ormlite-jdbc:${rootProject.extra["ormliteVersion"]}")
}