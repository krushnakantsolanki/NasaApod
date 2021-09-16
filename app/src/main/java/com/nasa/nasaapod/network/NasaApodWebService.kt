package com.nasa.nasaapod.network

import com.nasa.nasaapod.db.NasaApodEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApodWebService {

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(): Response<NasaApodEntity>

}