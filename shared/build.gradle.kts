plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    js {
        browser()
    }
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlinx.serialization.core)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}