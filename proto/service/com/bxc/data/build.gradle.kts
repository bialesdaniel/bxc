import com.google.protobuf.gradle.*

plugins {
  java
  idea
    id("com.google.protobuf") version "0.9.4"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val grpcVersion = "1.59.0" // CURRENT_GRPC_VERSION
val protobufVersion = "3.25.1"
val protocVersion = protobufVersion

dependencies {
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")

        if (JavaVersion.current().isJava9Compatible()) {
        // Workaround for @javax.annotation.Generated
        // see: https://github.com/grpc/grpc-java/issues/3633
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }

        testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protocVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                getByName("java") {
                    option("lite")
                }
            }
            it.plugins {
                create("grpc"){
                  option("lite")
                }
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("rpc")
        }
    }
}
