package com.studio.yami.kadesubmission.utils

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource

internal object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    val getEspressoIdlingResource: IdlingResource
        get() = espressoTestIdlingResource

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}