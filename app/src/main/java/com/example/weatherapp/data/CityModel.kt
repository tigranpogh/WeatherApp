package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("country") var country: String? = null,
    @SerializedName("city") var city: String? = null,
)