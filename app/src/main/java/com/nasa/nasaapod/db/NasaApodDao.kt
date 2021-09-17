package com.nasa.nasaapod.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface NasaApodDao {

    @Query("SELECT * FROM nasaapod ORDER BY date DESC")
    fun getAllPictures(): LiveData<List<NasaApodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(nasaApodEntity: NasaApodEntity)
}