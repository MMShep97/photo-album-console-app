plugins {
    java
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

application {
    mainClass.set("photoalbum.MainKt")
}

repositories {
    mavenCentral()
}

object Versions {
    const val ktor = "1.6.7"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-client-core:${Versions.ktor}")
    implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
    implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
    testImplementation("io.ktor:ktor-client-mock:${Versions.ktor}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("io.mockk:mockk:1.12.2")
}

// Output to build/libs/shadow.jar
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("photo-album-console-app")
    archiveClassifier.set("")
    archiveVersion.set("")
    minimize()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}