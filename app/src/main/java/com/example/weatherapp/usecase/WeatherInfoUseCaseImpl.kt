package com.example.weatherapp.usecase

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.DailyWeatherResponse
import com.example.weatherapp.data.ResponseState
import com.example.weatherapp.data.ForecastWeatherResponse
import com.example.weatherapp.database.MainModel
import com.example.weatherapp.repo.WeatherInfoRepo
import retrofit2.Response

class WeatherInfoUseCaseImpl(
    private val weatherInfoRepo: WeatherInfoRepo
) : WeatherInfoUseCase {

    override suspend fun getCurrentWeather(cityName: String): Response<CurrentWeatherResponse> {
        return weatherInfoRepo.getCurrentWeather(cityName)
    }

    override suspend fun getForecastWeather(cityName: String): DailyWeatherResponse {
        return weatherInfoRepo.getForecastWeather(cityName)
    }

    override suspend fun saveWeather(mainModel: MainModel) {
        return weatherInfoRepo.saveWeather(mainModel)
    }

    override suspend fun clearData() {
        weatherInfoRepo.clearData()
    }

    override fun getSavedWeather(): LiveData<List<MainModel>> {
        return weatherInfoRepo.getSavedWeather()
    }
}
