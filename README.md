# interview-ready-plugin

One-line Gradle plugin that wires up a full Android interview stack in seconds.

## What you get

Applying this plugin gives you **all** of the following, pre-configured and version-aligned:

| Category | Libraries |
|---|---|
| **UI** | Jetpack Compose (via BOM), Material 3, Navigation Compose |
| **DI** | kotlin-inject + KSP code gen |
| **Networking** | Retrofit 3 + OkHttp + Moshi **and** Ktor Client + KotlinX Serialization |
| **Persistence** | Room + KSP |
| **Images** | Coil 3 (Compose + OkHttp) |
| **Async** | Coroutines (android dispatcher) |
| **Lifecycle** | ViewModel Compose, Lifecycle Runtime Compose |
| **Testing** | JUnit, Coroutines Test, Turbine, Compose UI Test |

## Usage

### 1. In your project's `settings.gradle.kts`

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
```

### 2. In your app module's `build.gradle.kts`

```kotlin
plugins {
    id("io.github.manu.interview-app") version "1.0.0"
}

android {
    namespace = "com.example.myapp"
}

// That's it. All dependencies are applied.
// Optionally customize:
interviewApp {
    compileSdk = 35
    minSdk = 26

    // Toggle off what you don't need:
    enableRetrofit = false   // skip Retrofit stack
    enableKtor = false       // skip Ktor stack
    enableRoom = false       // skip Room
    enableCoil = false       // skip Coil
    enableKotlinInject = false // skip kotlin-inject
}
```

### 3. Start coding

No version catalog to copy, no dependency blocks to write. Open your Activity/Composable and go.

## Publishing to Gradle Plugin Portal

```bash
# First time: get your API key from https://plugins.gradle.org
# Put credentials in ~/.gradle/gradle.properties:
#   gradle.publish.key=YOUR_KEY
#   gradle.publish.secret=YOUR_SECRET

./gradlew publishPlugins
```

## Updating versions

All versions live in a single file:
`src/main/kotlin/com/manu/interview/Versions.kt`

Change a version there → publish a new plugin version → all consuming projects get the update on next sync.

## Plugin DSL extension

| Property | Default | Description |
|---|---|---|
| `compileSdk` | `35` | Android compile SDK |
| `minSdk` | `26` | Android min SDK |
| `targetSdk` | `35` | Android target SDK |
| `enableKotlinInject` | `true` | kotlin-inject runtime + KSP compiler |
| `enableRetrofit` | `true` | Retrofit 3 + OkHttp + Moshi + codegen |
| `enableKtor` | `true` | Ktor Client (OkHttp engine) + content negotiation |
| `enableRoom` | `true` | Room runtime + KTX + KSP compiler |
| `enableCoil` | `true` | Coil 3 Compose + OkHttp backend |
