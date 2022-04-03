plugins {
    kotlin("jvm") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val apiRelease by rootProject.extra { "1.1.8" }
val clientRelease by rootProject.extra { "1" }

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://raw.githubusercontent.com/MeteorLite/hosting/main/repo/") }
}

dependencies {
    implementation(kotlin("stdlib"))

    //Required libraries
    compileOnly(group = "meteor", name = "api-rs", version = apiRelease)
    compileOnly(group = "meteor", name = "api", version = apiRelease)
    compileOnly(group = "meteor", name = "http", version = apiRelease)
    compileOnly(group = "meteor", name = "annotations", version = apiRelease)
    compileOnly(group = "meteor", name = "logger", version = apiRelease)
    compileOnly(group = "meteor", name = "client", version = "$apiRelease-$clientRelease")
    compileOnly(group = "org.rationalityfrontline", name = "kevent", version = "2.1.2")
    implementation(group = "kext", name = "kext", version = "1.0.0")
}

tasks {
    compileKotlin {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()

        kotlinOptions {
            jvmTarget = "11"
            apiVersion = "1.6"
            languageVersion = "1.6"
            freeCompilerArgs += "-Xjvm-default=compatibility"
        }
    }
    jar {
        manifest {
            attributes["Main-Class"] = "meteor.plugins.external.ExternalPlugin"
        }
    }
}