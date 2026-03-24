package com.manu.interview

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class InterviewFeatureExtension @Inject constructor(objects: ObjectFactory) {

    /** Android SDK versions */
    val compileSdk: Property<Int> = objects.property(Int::class.java).convention(35)
    val minSdk: Property<Int> = objects.property(Int::class.java).convention(26)

    /** Set to false for pure data/domain modules that don't need Compose */
    val enableCompose: Property<Boolean> = objects.property(Boolean::class.java).convention(true)

    /** Toggle dependency groups */
    val enableKotlinInject: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    val enableMetro: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
    val enableRetrofit: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
    val enableKtor: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
    val enableRoom: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
    val enableCoil: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
}
