package com.nasa.nasaapod.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentDate(calendar: Calendar): String {
        return "${calendar.get(Calendar.YEAR)}-" +
                "${calendar.get(Calendar.MONTH) + 1}-" +
                "${calendar.get(Calendar.DATE)}"
    }

    fun convertStringToDate(givenDate: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var date = Date()
        try {
            date = format.parse(givenDate)
        } catch (e: ParseException) {
            Timber.e(e, "Error in converting date")
        }
        return date
    }

    fun convertDateFormat(date: Date?): String {
        if (date == null) return ""
        val spf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return spf.format(date)
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}