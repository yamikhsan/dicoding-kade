package com.studio.yami.kadesubmission.utils

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)