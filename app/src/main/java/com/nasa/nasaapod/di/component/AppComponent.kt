package com.nasa.nasaapod.di.component

import com.nasa.nasaapod.ApodApp
import com.nasa.nasaapod.di.module.ContextModule
import com.nasa.nasaapod.di.module.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, ContextModule::class]
)
interface AppComponent {
    fun inject(apodApp: ApodApp)
}