package com.nasa.nasaapod.ui

import androidx.lifecycle.ViewModel
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodViewModel @Inject constructor(private val nasaApodRepository: NasaApodRepository) :
    ViewModel() {

//    val nasaApodPicture = nasaApodRepository.getPicturebyDate()
    val nasaApodPicture = nasaApodRepository.getAllPictures()
//    fun fetchPictureByDate(date: String) = nasaApodRepository.fetchTodaysPicture()
//
//    lateinit var date: Date
//    val legoSet by lazy { nasaApodRepository.observeSet(date) }

}