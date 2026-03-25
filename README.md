# interview-ready-plugin

![Plugin Version](https://img.shields.io/gradle-plugin-portal/v/io.github.a-manumohan.interview-app)

Two Gradle plugins that wire up a full Android interview stack in seconds — one for the app module, one for feature modules.

## Plugins

| Plugin ID | Apply to |
|---|---|
| `io.github.a-manumohan.interview-app` | The single `:app` module |
| `io.github.a-manumohan.interview-feature` | Any `:feature-*` library module |

---

## `interview-app` — What you get

Applying this plugin gives you **all** of the following, pre-configured and version-aligned:

| Category | Libraries |
|---|---|
| **UI** | Jetpack Compose (via BOM), Material 3, Navigation Compose |
| **DI** | kotlin-inject + KSP code gen *(or Metro — opt-in)* |
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

### 2. App module `build.gradle.kts`

```kotlin
plugins {
    id("io.github.a-manumohan.interview-app") version "1.0.6"
}

android {
    namespace = "com.example.myapp"
    // Set SDK versions here — the plugin applies sensible defaults (compileSdk/targetSdk 35, minSdk 26)
    // which you can override directly in this block if needed.
}

// That's it. All dependencies are applied.
// Optionally customize:
interviewApp {
    // Toggle off what you don't need:
    enableRetrofit = false     // skip Retrofit stack
    enableKtor = false         // skip Ktor stack
    enableRoom = false         // skip Room
    enableCoil = false         // skip Coil
    enableKotlinInject = false // skip kotlin-inject
    enableMetro = true         // opt-in: use Metro DI instead of kotlin-inject
}
```

### 3. Feature module `build.gradle.kts`

```kotlin
plugins {
    id("io.github.a-manumohan.interview-feature") version "1.0.6"
}

android {
    namespace = "com.example.myapp.feature.login"
    // Override SDK versions here if needed.
}

// Optionally customize:
interviewFeature {
    // enableCompose controls Compose dependencies only.
    // buildFeatures.compose is always enabled; to also disable it for a
    // data/domain module, add: android { buildFeatures { compose = false } }
    enableCompose = false        // opt-out: skip Compose dependencies
    enableKotlinInject = true    // true by default
    enableMetro = false          // opt-in: use Metro DI instead of kotlin-inject
    enableKtor = true            // opt-in: add Ktor networking
    enableRetrofit = false       // opt-in: add Retrofit stack
    enableRoom = false           // opt-in: add Room
    enableCoil = false           // opt-in: add Coil
}
```

### 4. Start coding

No version catalog to copy, no dependency blocks to write. Open your Activity/Composable and go.

## Publishing to Gradle Plugin Portal

Publishing is triggered automatically by pushing a version tag:

```bash
git tag v1.x.x && git push origin v1.x.x
```

The GitHub Action will run `./gradlew publishPlugins` using the stored `GRADLE_PUBLISH_KEY` / `GRADLE_PUBLISH_SECRET` secrets.

## Updating versions

All versions live in a single file:
`src/main/kotlin/com/manu/interview/Versions.kt`

Change a version there → publish a new plugin version → all consuming projects get the update on next sync.

## DSL reference

### `interviewApp { }`

| Property | Default | Description |
|---|---|---|
| `enableKotlinInject` | `true` | kotlin-inject runtime + KSP compiler |
| `enableMetro` | `false` | Metro DI plugin + KSP compiler (opt-in alternative to kotlin-inject) |
| `enableRetrofit` | `true` | Retrofit 3 + OkHttp + Moshi + codegen |
| `enableKtor` | `true` | Ktor Client (OkHttp engine) + content negotiation |
| `enableRoom` | `true` | Room runtime + KTX + KSP compiler |
| `enableCoil` | `true` | Coil 3 Compose + OkHttp backend |

> SDK versions (`compileSdk` 35, `minSdk` 26, `targetSdk` 35) are applied as defaults. Override them in your `android { }` block if needed.

### `interviewFeature { }`

| Property | Default | Description |
|---|---|---|
| `enableCompose` | `true` | Compose BOM, Material 3, Navigation Compose, UI tooling. Note: `buildFeatures.compose` is always enabled by the plugin; set `android { buildFeatures { compose = false } }` in addition if you need to fully disable it. |
| `enableKotlinInject` | `true` | kotlin-inject runtime + KSP compiler |
| `enableMetro` | `false` | Metro DI plugin + KSP compiler (opt-in alternative to kotlin-inject) |
| `enableRetrofit` | `false` | Retrofit 3 + OkHttp + Moshi + codegen |
| `enableKtor` | `false` | Ktor Client (OkHttp engine) + content negotiation |
| `enableRoom` | `false` | Room runtime + KTX + KSP compiler |
| `enableCoil` | `false` | Coil 3 Compose + OkHttp backend |

> SDK versions (`compileSdk` 35, `minSdk` 26) are applied as defaults. Override them in your `android { }` block if needed.
