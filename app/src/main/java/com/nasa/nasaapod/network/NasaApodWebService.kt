package com.nasa.nasaapod.network

import retrofit2.http.GET

interface NasaApodWebService {

    @GET("planetary/apod")
    fun getPictureOfTheDay()
}