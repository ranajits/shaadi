package com.ranajit.shaadi.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ranajit.shaadi.ShaadiApplication
import com.ranajit.shaadi.data.NetworkDataProvider
import com.ranajit.shaadi.networking.RetrofitClient
import com.ranajit.shaadi.ui.homescreen.viewmodel.HomeViewModel

open class BaseFragment : Fragment() {
    fun <T> setupViewModel(
        viewModelStoreOwner: ViewModelStoreOwner,
        viewModel: Class<T>
    ): ViewModel? {

        var vm: ViewModel? = null
        if (viewModel.isAssignableFrom(HomeViewModel::class.java))
            vm = ViewModelProvider(
                viewModelStoreOwner,
                ViewModelFactory(
                    NetworkDataProvider(RetrofitClient.MAIN_SERVICE),
                    ShaadiApplication.instance
                )
            ).get(HomeViewModel::class.java)
        return vm
    }
}