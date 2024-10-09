rootProject.name = "spring-react-fullstack-app"

pluginManagement {
    repositories {
        mavenCentral()
    }

    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("kotlinx") {
            val kotlinxSerializationVersion = providers.gradleProperty("kotlinx.serialization.version").get()
            val kotlinxCoroutinesVersion = providers.gradleProperty("kotlinx.coroutines.version").get()
            library("serialization-core", "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
            library("serialization-json", "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
            library("coroutines-core", "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
        }

        create("kotlinWrappers") {
            val kotlinWrapperVersion = providers.gradleProperty("kotlin.wrappers.version").get()
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$kotlinWrapperVersion")
        }

        create("ktor") {
            val ktorVersion = providers.gradleProperty("ktor.version").get()
            library("client-core", "io.ktor:ktor-client-core:$ktorVersion")
            library("client-content-negotiation", "io.ktor:ktor-client-content-negotiation:$ktorVersion")
            library("serialization-kotlinx-json","io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        }
    }
}
include("frontend")
include("backend")
include("shared")
