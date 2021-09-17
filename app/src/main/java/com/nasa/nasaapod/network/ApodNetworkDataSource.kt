package com.nasa.nasaapod.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodNetworkDataSource @Inject constructor(
    private val nasaApodWebService: NasaApodWebService
) : BaseDataSource(){
    suspend fun fetchNasaApod()
            = getResult { nasaApodWebService.getPictureOfTheDay() }
}