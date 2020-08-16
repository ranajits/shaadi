package com.ranajit.shaadi

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import java.lang.ref.WeakReference

class ShaadiApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        wApp!!.clear()
        wApp = WeakReference(this@ShaadiApplication)

    }

    companion object {
        private var wApp: WeakReference<ShaadiApplication>? =
            WeakReference<ShaadiApplication>(null)!!
        val instance: ShaadiApplication get() = wApp?.get()!!

        val context: Context
            get() {
                val app = if (null != wApp) wApp!!.get() else ShaadiApplication()
                return if (app != null) app.applicationContext else ShaadiApplication().applicationContext
            }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}