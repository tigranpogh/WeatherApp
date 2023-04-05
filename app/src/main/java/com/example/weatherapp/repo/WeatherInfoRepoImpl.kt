package com.example.weatherapp.repo

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.network.WeatherInfoApi
import com.example.weatherapp.data.DailyWeatherResponse
import com.example.weatherapp.database.MainModel
import com.example.weatherapp.database.WeatherDAO
import retrofit2.Response

class WeatherInfoRepoImpl(
    private val weatherInfoApi: WeatherInfoApi,
    private val weatherDAO: WeatherDAO
) : WeatherInfoRepo {

    override suspend fun getCurrentWeather(cityName: String): Response<CurrentWeatherResponse> {
        return weatherInfoApi.getCurrentWeather(cityName)
    }

    //Add fake data for forecast weather(because this is payed api)
    override suspend fun getForecastWeather(cityName: String): DailyWeatherResponse {
        return  DailyWeatherResponse()
    }

    override suspend fun saveWeather(mainModel: MainModel) {
        weatherDAO.insert(mainModel)
    }

    override suspend fun clearData() {
        weatherDAO.deleteAll()
    }

    override fun getSavedWeather(): LiveData<List<MainModel>> {
        return weatherDAO.all
    }
}
