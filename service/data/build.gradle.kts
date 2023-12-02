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
    implementation("io.grpc:grpc-services:$grpcVersion")
    implementation("io.perfmark:perfmark-api:0.26.0")

    if (JavaVersion.current().isJava9Compatible()) {
        // Workaround for @javax.annotation.Generated
        // see: https://github.com/grpc/grpc-java/issues/3633
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
}

tasks.named<Test>("test") {
    testLogging.showStandardStreams = true
}
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.bxc.data.DataServer"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE    

    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

sourceSets {
    main {
        proto {
            srcDir("../../proto/service/com/bxc/data")
        }
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")

            // For docker build.
            srcDirs("proto/grpc")
            srcDirs("proto/java")
        }
    }
    test {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}
