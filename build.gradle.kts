plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "2.1.0"
}

group = "io.github.manu" // Use your GitHub username for Gradle Plugin Portal
version = "1.0.0"

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    // These are needed so the convention plugin can apply AGP + Kotlin + KSP plugins
    implementation("com.android.tools.build:gradle:8.8.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.3.6")
}

gradlePlugin {
    website = "https://github.com/manu/interview-ready-plugin"
    vcsUrl = "https://github.com/manu/interview-ready-plugin"

    plugins {
        create("interviewApp") {
            id = "io.github.manu.interview-app"
            displayName = "Interview-Ready Android App Plugin"
            description = "One-line Gradle plugin that wires up Compose, kotlin-inject, Retrofit, Ktor, Room, Coil, Navigation, and Coroutines for rapid Android app scaffolding."
            tags = listOf("android", "compose", "interview", "scaffold", "kotlin")
            implementationClass = "com.manu.interview.InterviewAppPlugin"
        }
        create("interviewFeature") {
            id = "io.github.manu.interview-feature"
            displayName = "Interview-Ready Android Feature Module Plugin"
            description = "One-line Gradle plugin that configures an Android library module with Compose, kotlin-inject, Navigation, and Coroutines for rapid feature module scaffolding."
            tags = listOf("android", "compose", "interview", "feature", "library", "kotlin")
            implementationClass = "com.manu.interview.InterviewFeaturePlugin"
        }
    }
}
