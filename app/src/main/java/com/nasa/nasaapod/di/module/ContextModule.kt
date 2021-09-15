package com.nasa.nasaapod.di.module

import android.content.Context
import com.nasa.nasaapod.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private var context: Context) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = context
}