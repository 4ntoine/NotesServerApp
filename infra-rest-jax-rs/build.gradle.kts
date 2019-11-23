dependencies {
    api(project(":app-api"))
    implementation("javax.ws.rs:javax.ws.rs-api:2.0") // JAX-WS spec
    implementation("javax.enterprise:cdi-api:2.0.SP1") // CDI spec
    implementation("javax.transaction:javax.transaction-api:1.2") // JTA spec
}

allOpen {
    annotation("javax.ws.rs.Path")
}