@Suppress("DSL_SCOPE_VIOLATION") // just to avoid "libs" red underline
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

group = "dev.letter"

kotlin {
    jvm()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation(libs.serialization)
                implementation(libs.coroutines)
                implementation(libs.bundles.ktor.main)
            }
        }

        val jvmMain by getting{
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}