package com.studio.yami.kadesubmission.sourcedata.api

import com.studio.yami.kadesubmission.BuildConfig
import java.net.URL

fun apiRequest(url: String): String = URL(url).readText()

fun myUrl(path: String, p: String, query: String): String{
    return BuildConfig.BASE_URL + path + ".php?${p}=" + query
}