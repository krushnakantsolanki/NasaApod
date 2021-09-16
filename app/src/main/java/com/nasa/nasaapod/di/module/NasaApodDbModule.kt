package com.nasa.nasaapod.di.module

import android.content.Context
import androidx.room.Room
import com.nasa.nasaapod.db.NasaApodDao
import com.nasa.nasaapod.db.NasaApodDb
import com.nasa.nasaapod.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NasaApodDbModule {
    @Provides
    @Singleton
    fun provideApodDb(@ApplicationContext context: Context): NasaApodDb = Room
        .databaseBuilder(context, NasaApodDb::class.java, "apodapp.db")
        .build()

    @Provides
    @Singleton
    fun provideApodDao(apodDb: NasaApodDb): NasaApodDao = apodDb.NasaApodDao()

}
