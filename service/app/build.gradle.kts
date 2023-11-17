/*
 * User Manual available at https://docs.gradle.org/8.0.2/userguide/building_java_projects.html
 */

import com.google.protobuf.gradle.*

plugins {
    java
    idea
    id("com.google.protobuf") version "0.9.4"
}

repositories {
    // google()
    mavenCentral()
}

val grpcVersion = "1.59.0" // CURRENT_GRPC_VERSION
val protobufVersion = "3.25.1"
val protocVersion = protobufVersion

dependencies {
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.grpc:grpc-netty-shaded:${grpcVersion}")

    compileOnly("org.apache.tomcat:annotations-api:6.0.53")


    testImplementation("io.grpc:grpc-testing:${grpcVersion}")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.4.0")
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:${protocVersion}"
  }
  plugins {
    id("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
    }
  }
  generateProtoTasks {
    all().forEach {
      it.plugins {
        create("grpc")
      }
    }
  }

  sourceSets{
    main{
        proto{
            srcDirs("src/main/java/bxc/analysis/service/proto")
        }
        java{
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
  }
}
