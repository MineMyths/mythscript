plugins {
    application
    `maven-publish`
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
}

group = "me.omega"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    compileOnly("dev.hollowcube:minestom-ce:dev")
    compileOnly("me.omega:mythcommons:1.0")

    compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation("org.graalvm.js:js:23.0.1")
}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    build {
        dependsOn(publishToMavenLocal)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}