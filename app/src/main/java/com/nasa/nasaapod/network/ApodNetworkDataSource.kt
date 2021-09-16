package com.nasa.nasaapod.network

import androidx.lifecycle.MutableLiveData
import com.nasa.nasaapod.BuildConfig
import com.nasa.nasaapod.db.NasaApodEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodNetworkDataSource @Inject constructor(
    private val nasaApodWebService: NasaApodWebService
) {
    private val apodMutableLiveData = MutableLiveData<NasaApodEntity>()
    fun getApodMutableLiveData() = apodMutableLiveData

    fun fetchApodData() {
        Timber.e("data fetched for data")
        val apodService = nasaApodWebService.getPictureOfTheDay()
        apodService.enqueue(object : Callback<NasaApodEntity> {
            override fun onResponse(call: Call<NasaApodEntity>, response: Response<NasaApodEntity>) {
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()?.mediaType == "image")
                        apodMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<NasaApodEntity>, t: Throwable) {
                Timber.e(t, "onFailure caught")
            }
        })
    }
}