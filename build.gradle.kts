import proguard.gradle.ProGuardTask

plugins {
    java
    kotlin("jvm") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val apiRelease by rootProject.extra { "1.3.0" }
val clientRelease by rootProject.extra { "2" }
val pluginClass by rootProject.extra { "meteor.plugins.external.ExternalPlugin" }

val outputJar by rootProject.extra { "./build/libs/${rootProject.name}-${version}.jar" }
val obfuscatedJar by rootProject.extra { "./build/libs/${rootProject.name}-proguard.jar" }
val exportDir by rootProject.extra { "${System.getProperty("user.home")}/.meteor/externalplugins/" }

buildscript {
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.2.1")
    }
}

repositories {
    mavenLocal()
    maven { url = uri("https://raw.githubusercontent.com/MeteorLite/hosting/main/repo/") }
    mavenCentral()
}

dependencies {
    //Required libraries
    compileOnly(group = "meteor", name = "api-rs", version = apiRelease)
    compileOnly(group = "meteor", name = "api", version = apiRelease)
    compileOnly(group = "meteor", name = "http", version = apiRelease)
    compileOnly(group = "meteor", name = "annotations", version = apiRelease)
    compileOnly(group = "meteor", name = "logger", version = apiRelease)
    compileOnly(group = "meteor", name = "client", version = "$apiRelease-$clientRelease")
    compileOnly(group = "org.rationalityfrontline", name = "kevent", version = "2.1.4")
}

tasks {
    compileJava {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }

    compileKotlin {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()

        kotlinOptions {
            jvmTarget = "17"
            apiVersion = "1.6"
            languageVersion = "1.6"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }
    jar {
        finalizedBy(":export")
        manifest {
            attributes["Main-Class"] = pluginClass
        }
    }
}

tasks.register<ProGuardTask>("proguard") {
    dependsOn(":jar")
    configuration("proguard.conf")
    injars(outputJar)
    outjars(obfuscatedJar)
    libraryjars(project.configurations.compileClasspath.files)
}

tasks.register<Copy>("export") {
    dependsOn(":proguard")
    from(obfuscatedJar)
    into(exportDir)
    rename("${rootProject.name}-proguard.jar", "${rootProject.name}.jar")
}