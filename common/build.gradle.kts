import org.jetbrains.kotlin.gradle.targets.js.dukat.DukatTask

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

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.serialization)
                implementation(libs.coroutines)
                implementation(libs.bundles.ktor.main)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}

tasks.withType<DukatTask> { enabled = false }