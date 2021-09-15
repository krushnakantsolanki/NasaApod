package com.nasa.nasaapod.ui

import com.nasa.nasaapod.execute.AppExecutors
import com.nasa.nasaapod.network.ApodNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodRepository @Inject constructor(
    private val apodNetworkDataSource: ApodNetworkDataSource,
    private val executors: AppExecutors
) {


}