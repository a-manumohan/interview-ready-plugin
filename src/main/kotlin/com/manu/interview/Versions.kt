package com.manu.interview

/**
 * Single source of truth for all dependency versions.
 * Update this file to bump versions across all consuming projects.
 */
object Versions {
    // Compose
    const val COMPOSE_BOM = "2026.03.00"

    // Core AndroidX
    const val CORE_KTX = "1.16.0"
    const val LIFECYCLE = "2.9.0"
    const val ACTIVITY_COMPOSE = "1.10.1"
    const val NAVIGATION = "2.9.0"

    // Coroutines
    const val COROUTINES = "1.10.2"

    // Serialization
    const val SERIALIZATION_JSON = "1.8.1"

    // DI — kotlin-inject
    const val KOTLIN_INJECT = "0.9.0"

    // DI — Metro
    const val METRO = "0.10.2"

    // Networking — Retrofit stack
    const val RETROFIT = "3.0.0"
    const val MOSHI = "1.15.2"
    const val OKHTTP = "4.12.0"

    // Networking — Ktor stack
    const val KTOR = "3.4.0"

    // Persistence
    const val ROOM = "2.8.4"

    // Image loading
    const val COIL = "3.1.0"

    // Testing
    const val TURBINE = "1.2.0"
}
