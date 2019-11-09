import com.google.protobuf.gradle.*

plugins {
    id("com.google.protobuf")
}

dependencies {
    api(project(":app-api"))

    implementation("io.grpc:grpc-netty-shaded:${rootProject.extra["grpcVersion"]}")
    implementation("io.grpc:grpc-protobuf:${rootProject.extra["grpcVersion"]}")
    implementation("io.grpc:grpc-stub:${rootProject.extra["grpcVersion"]}")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.5.1-1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${rootProject.extra["grpcVersion"]}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}