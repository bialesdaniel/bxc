import com.google.protobuf.gradle.*

plugins {
    id("java-library")
    id("com.google.protobuf") version "0.9.4"
    idea
}

repositories {
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

    protobuf(files("${project(":").getDependencyProject().projectDir}/src/main/proto/com/bxc/service/shared/example"))
    // testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    // testImplementation("junit:junit:4.13.2")
    // testImplementation("io.grpc:grpc-testing:$grpcVersion")
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
            it.generateDescriptorSet = true
            it.descriptorSetOptions.includeImports =true
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("${project(":src:main:proto").projectDir}/com/bxc/service/stats/rpc")
        }
    }
}
