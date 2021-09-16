package com.nasa.nasaapod.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface NasaApodDao {

    @Query("SELECT * FROM nasaapod ORDER BY date DESC")
    fun getAllPictures(): LiveData<List<NasaApodEntity>>

    @Query("SELECT * FROM nasaapod WHERE date = :date")
    fun getPictureLiveDataByDate(date: Date): LiveData<NasaApodEntity>

    @Query("SELECT * FROM nasaapod WHERE date = :date")
    fun getPictureEntityByDate(date: Date): NasaApodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(nasaApodEntity: NasaApodEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePicture(nasaApodEntity: NasaApodEntity)
}