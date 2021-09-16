package com.nasa.nasaapod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NasaApodEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class NasaApodDb : RoomDatabase() {

    abstract fun NasaApodDao(): NasaApodDao
}