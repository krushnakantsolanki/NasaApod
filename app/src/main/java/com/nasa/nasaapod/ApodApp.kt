package com.nasa.nasaapod

import android.app.Application
import com.nasa.nasaapod.di.component.AppComponent
import com.nasa.nasaapod.di.component.DaggerAppComponent
import com.nasa.nasaapod.di.module.ContextModule

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
    }

    fun getApodAppComponent(): AppComponent = appComponent
}