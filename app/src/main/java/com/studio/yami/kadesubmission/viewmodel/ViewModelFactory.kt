package com.studio.yami.kadesubmission.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.studio.yami.kadesubmission.repository.ApiRepository
import com.studio.yami.kadesubmission.repository.LocalRepository
import com.studio.yami.kadesubmission.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application): ViewModelProvider.Factory {

    private val server = ApiRepository()
    private val local = LocalRepository(application)
    private val repo = MainRepository(server, local)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}