package com.example.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.weatherapp.data.CityModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object Utils {
    fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    fun getCitiesList(context: Context): List<String> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("city-list.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.message
        }

        val listCountryType = object : TypeToken<List<CityModel>>() {}.type
        return Gson().fromJson<List<CityModel>>(jsonString, listCountryType).map { it.city ?: "" }
    }
}