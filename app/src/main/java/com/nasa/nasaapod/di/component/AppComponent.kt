package com.nasa.nasaapod.di.component

import ApodService.di.module.ViewModelModule
import com.nasa.nasaapod.ApodApp
import com.nasa.nasaapod.di.module.NasaApodDbModule
import com.nasa.nasaapod.di.module.ContextModule
import com.nasa.nasaapod.di.module.RetrofitModule
import com.nasa.nasaapod.ui.NasaApodFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, NasaApodDbModule::class, ContextModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(apodApp: ApodApp)

    fun inject(nasaApodFragment: NasaApodFragment)

}