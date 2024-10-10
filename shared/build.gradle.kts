plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlinx.serialization.core)
                implementation(kotlinx.serialization.json)
                implementation(kotlin("reflect"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmMain {
            dependencies {
                implementation("commons-codec:commons-codec:1.17.1")
            }
        }
    }
}