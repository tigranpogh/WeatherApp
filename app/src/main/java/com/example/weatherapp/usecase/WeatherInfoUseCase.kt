package com.example.weatherapp.usecase

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.DailyWeatherResponse
import com.example.weatherapp.data.ResponseState
import com.example.weatherapp.data.ForecastWeatherResponse
import com.example.weatherapp.database.MainModel
import retrofit2.Response

interface WeatherInfoUseCase {
    suspend fun getCurrentWeather(cityName: String): Response<CurrentWeatherResponse>

    suspend fun getForecastWeather(cityName: String): DailyWeatherResponse

    suspend fun saveWeather(mainModel: MainModel)

    suspend fun clearData()

    fun getSavedWeather(): LiveData<List<MainModel>>
}
