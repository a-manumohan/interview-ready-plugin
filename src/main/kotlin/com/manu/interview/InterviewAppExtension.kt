package com.manu.interview

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class InterviewAppExtension @Inject constructor(objects: ObjectFactory) {

    /** Android SDK versions */
    val compileSdk: Property<Int> = objects.property(Int::class.java).convention(35)
    val minSdk: Property<Int> = objects.property(Int::class.java).convention(26)
    val targetSdk: Property<Int> = objects.property(Int::class.java).convention(35)

    /** Toggle dependency groups — all enabled by default */
    val enableKotlinInject: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    val enableRetrofit: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    val enableKtor: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    val enableRoom: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
    val enableCoil: Property<Boolean> = objects.property(Boolean::class.java).convention(true)
}
