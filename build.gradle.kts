plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.0"
    id("io.micronaut.test-resources") version "4.4.0"
    id("io.micronaut.aot") version "4.4.0"
    id("java")
    id("groovy") // For Spock tests
}

version = "0.1"
group = "aviation.backend"

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {

    val micronautVersion = "4.4.0"
    val spockVersion = "2.3-groovy-4.0"

    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.mongodb:micronaut-mongo-reactive")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("io.micronaut.test:micronaut-test-spock:$micronautVersion")
    testImplementation("org.spockframework:spock-core:$spockVersion")
    testImplementation("io.micronaut:micronaut-http-client")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


micronaut {
    runtime("netty")
    testRuntime("spock")
    processing {
        incremental(true)
        annotations("aviation.backend.*")
    }
    aot {
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}
