package com.nasa.nasaapod

import android.app.Application
import com.nasa.nasaapod.di.component.AppComponent
import com.nasa.nasaapod.di.component.DaggerAppComponent
import com.nasa.nasaapod.di.module.ContextModule
import timber.log.Timber

class ApodApp : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: ApodApp
            private set
    }

    private val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent.inject(this)
        initTimber()
    }
    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
    fun getApodAppComponent(): AppComponent = appComponent
}