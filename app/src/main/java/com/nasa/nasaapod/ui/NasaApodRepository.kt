package com.nasa.nasaapod.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.nasa.nasaapod.data.resultLiveData
import com.nasa.nasaapod.db.NasaApodDao
import com.nasa.nasaapod.db.NasaApodEntity
import com.nasa.nasaapod.network.ApodNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodRepository @Inject constructor(
    private val nasaapodDao: NasaApodDao,
    private val apodNetworkDataSource: ApodNetworkDataSource,
) {
    fun observeApod() = resultLiveData(
        databaseQuery = { nasaapodDao.getAllPictures() },
        networkCall = { apodNetworkDataSource.fetchNasaApod() },
        saveCallResult = { nasaapodDao.insertPicture(it) })
        .distinctUntilChanged()
}

