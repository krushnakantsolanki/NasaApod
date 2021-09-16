package com.nasa.nasaapod.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.nasa.nasaapod.data.resultLiveData
import com.nasa.nasaapod.db.NasaApodDao
import com.nasa.nasaapod.db.NasaApodEntity
import com.nasa.nasaapod.utils.AppExecutors
import com.nasa.nasaapod.network.ApodNetworkDataSource
import com.nasa.nasaapod.utils.Utils
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodRepository @Inject constructor(
    private val nasaapodDao: NasaApodDao,
    private val apodNetworkDataSource: ApodNetworkDataSource,
    private val executors: AppExecutors
) {
//    init {
//        fetchPictureByDateDb()
//        val apodLiveData = apodNetworkDataSource.getApodMutableLiveData()
//        apodLiveData.observeForever {
//            executors.diskIO().execute {
//                nasaapodDao.insertPicture(it)
//            }
//        }
//    }


//    fun getPicturebyDate(): LiveData<NasaApodEntity> {
//        val cal = Calendar.getInstance()
//        return nasaapodDao.getPictureLiveDataByDate( Utils.convertStringToDate(Utils.getCurrentDate(cal)))
//    }

    fun getAllPictures(): LiveData<List<NasaApodEntity>> {
        return nasaapodDao.getAllPictures()
    }
//    fun fetchTodaysPicture() {
//        apodNetworkDataSource.fetchApodData()
//    }

    fun observeApod() = resultLiveData(
        databaseQuery = { nasaapodDao.getAllPictures() },
        networkCall = { apodNetworkDataSource.fetchNasaApod() },
        saveCallResult = { nasaapodDao.insertPicture(it) })
        .distinctUntilChanged()

//    fun fetchPictureByDateDb() {
//        val cal = Calendar.getInstance()
//        executors.diskIO().execute {
//            val apodEntity = nasaapodDao.getPictureEntityByDate(
//                Utils.convertStringToDate(Utils.getCurrentDate(cal))
//            )
//            if (apodEntity == null) fetchTodaysPicture()
//        }
//    }

//    fun observeSet(date: Date) = resultLiveData(
//        databaseQuery = { nasaapodDao.getPictureLiveDataByDate(date) },
//        networkCall = { apodNetworkDataSource.fetchApodData() },
//        saveCallResult = { nasaapodDao.insertPicture(it) })
//        .distinctUntilChanged()

}

