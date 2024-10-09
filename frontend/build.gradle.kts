import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport { enabled = true }
                scssSupport { enabled = true }
                devServer?.port = 3000
                devServer?.open = false
                devServer?.proxy = mutableListOf(
                    KotlinWebpackConfig.DevServer.Proxy(
                        context = mutableListOf("/api"),
                        target = "http://localhost:8080"
                    )
                )
                configDirectory = File(projectDir, "webpack.config.d")
                outputFileName = "/static/js/app.js"
            }
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation(kotlinWrappers.emotion)
                implementation(kotlinWrappers.react)
                implementation(kotlinWrappers.reactDom)
                implementation(kotlinWrappers.reactRouterDom)
                implementation(kotlinWrappers.mui.material)
                implementation(kotlinWrappers.mui.system)
                implementation(kotlinWrappers.mui.iconsMaterial)
                implementation(kotlinx.serialization.core)
                implementation(kotlinx.serialization.json)
                implementation(kotlinx.coroutines.core)
                implementation(ktor.client.core)
                implementation(ktor.client.content.negotiation)
                implementation(ktor.serialization.kotlinx.json)
                implementation(npm("zustand", "4.5.5"))
                implementation(npm("notistack", "3.0.1"))
                implementation(project(":shared"))
            }
        }

        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}