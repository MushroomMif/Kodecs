plugins {
    kotlin("jvm") version "1.9.23"
    application
    `maven-publish`
}

group = "me.mushroommif"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://libraries.minecraft.net") }
}

dependencies {
    api("com.mojang:datafixerupper:7.0.14")
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.mushroommif"
            artifactId = "kodecs"
            version = version

            from(components["java"])
        }
    }
}