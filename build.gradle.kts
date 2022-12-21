import org.jetbrains.kotlin.gradle.targets.js.dukat.DukatTask

@Suppress("DSL_SCOPE_VIOLATION") // just to avoid "libs" red underline
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }

    // disable unnecessary task for minor performance boost
    tasks.withType<DukatTask>().configureEach { enabled = false }
}