package com.nasa.nasaapod.ui

import androidx.lifecycle.ViewModel
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodViewModel @Inject constructor(private val nasaApodRepository: NasaApodRepository) :
    ViewModel() {

    val nasaApod by lazy { nasaApodRepository.observeApod() }
    }