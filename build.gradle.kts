group = "de.kiridevs"
version = "1.3.1"
description = "kiriSMP Plugin"
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()

    // The SpigotMC Maven Repository
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    mavenCentral()
}

dependencies {
    api("org.jetbrains:annotations:23.0.0")
    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
}

tasks {
    withType<ProcessResources> {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }       
    }
}
