package com.ranajit.shaadi.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranajit.shaadi.data.MainRepository
import com.ranajit.shaadi.data.NetworkDataProvider
import com.ranajit.shaadi.ui.homescreen.viewmodel.HomeViewModel

class ViewModelFactory<T>(private val dataProvider: T, private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                application,
                MainRepository(dataProvider as NetworkDataProvider)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

