package com.manu.interview

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

@Suppress("UnstableApiUsage")
class InterviewAppPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            val ext = extensions.create("interviewApp", InterviewAppExtension::class.java)

            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            pluginManager.apply("com.google.devtools.ksp")

            afterEvaluate {
                if (ext.enableMetro.get()) {
                    pluginManager.apply("dev.zacsweers.metro")
                }
                configureAndroid(ext)
                configureDependencies(ext)
            }
        }
    }

    private fun Project.configureAndroid(ext: InterviewAppExtension) {
        extensions.configure<ApplicationExtension> {
            compileSdk = ext.compileSdk.get()
            defaultConfig {
                minSdk = ext.minSdk.get()
                targetSdk = ext.targetSdk.get()
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            buildFeatures {
                compose = true
            }
        }
    }

    private fun Project.configureDependencies(ext: InterviewAppExtension) {
        val v = Versions

        dependencies {
            // ── Compose (via BOM) ──────────────────────────────────────
            add("implementation", platform("androidx.compose:compose-bom:${v.COMPOSE_BOM}"))
            add("implementation", "androidx.compose.ui:ui")
            add("implementation", "androidx.compose.ui:ui-tooling-preview")
            add("implementation", "androidx.compose.material3:material3")
            add("implementation", "androidx.compose.foundation:foundation")
            add("implementation", "androidx.compose.runtime:runtime")
            add("debugImplementation", "androidx.compose.ui:ui-tooling")

            // ── Core AndroidX ──────────────────────────────────────────
            add("implementation", "androidx.core:core-ktx:${v.CORE_KTX}")
            add("implementation", "androidx.lifecycle:lifecycle-runtime-compose:${v.LIFECYCLE}")
            add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:${v.LIFECYCLE}")
            add("implementation", "androidx.activity:activity-compose:${v.ACTIVITY_COMPOSE}")
            add("implementation", "androidx.navigation:navigation-compose:${v.NAVIGATION}")

            // ── Coroutines ─────────────────────────────────────────────
            add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:${v.COROUTINES}")

            // ── Serialization ──────────────────────────────────────────
            add("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json:${v.SERIALIZATION_JSON}")

            // ── kotlin-inject (DI) ─────────────────────────────────────
            if (ext.enableKotlinInject.get()) {
                add("implementation", "me.tatarka.inject:kotlin-inject-runtime:${v.KOTLIN_INJECT}")
                add("ksp", "me.tatarka.inject:kotlin-inject-compiler-ksp:${v.KOTLIN_INJECT}")
            }

            // ── Retrofit + OkHttp + Moshi ──────────────────────────────
            if (ext.enableRetrofit.get()) {
                add("implementation", "com.squareup.retrofit2:retrofit:${v.RETROFIT}")
                add("implementation", "com.squareup.retrofit2:converter-moshi:${v.RETROFIT}")
                add("implementation", "com.squareup.moshi:moshi-kotlin:${v.MOSHI}")
                add("ksp", "com.squareup.moshi:moshi-kotlin-codegen:${v.MOSHI}")
                add("implementation", "com.squareup.okhttp3:okhttp:${v.OKHTTP}")
                add("implementation", "com.squareup.okhttp3:logging-interceptor:${v.OKHTTP}")
            }

            // ── Ktor Client ────────────────────────────────────────────
            if (ext.enableKtor.get()) {
                add("implementation", "io.ktor:ktor-client-core:${v.KTOR}")
                add("implementation", "io.ktor:ktor-client-okhttp:${v.KTOR}")
                add("implementation", "io.ktor:ktor-client-content-negotiation:${v.KTOR}")
                add("implementation", "io.ktor:ktor-serialization-kotlinx-json:${v.KTOR}")
                add("implementation", "io.ktor:ktor-client-logging:${v.KTOR}")
            }

            // ── Room ───────────────────────────────────────────────────
            if (ext.enableRoom.get()) {
                add("implementation", "androidx.room:room-runtime:${v.ROOM}")
                add("implementation", "androidx.room:room-ktx:${v.ROOM}")
                add("ksp", "androidx.room:room-compiler:${v.ROOM}")
            }

            // ── Coil (image loading) ───────────────────────────────────
            if (ext.enableCoil.get()) {
                add("implementation", "io.coil-kt.coil3:coil-compose:${v.COIL}")
                add("implementation", "io.coil-kt.coil3:coil-network-okhttp:${v.COIL}")
            }

            // ── Testing essentials ─────────────────────────────────────
            add("testImplementation", "junit:junit:4.13.2")
            add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:${v.COROUTINES}")
            add("testImplementation", "app.cash.turbine:turbine:${v.TURBINE}")
            add("androidTestImplementation", platform("androidx.compose:compose-bom:${v.COMPOSE_BOM}"))
            add("androidTestImplementation", "androidx.compose.ui:ui-test-junit4")
        }
    }
}
