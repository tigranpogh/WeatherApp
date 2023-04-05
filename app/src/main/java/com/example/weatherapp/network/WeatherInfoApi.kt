package com.example.weatherapp.network

import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.ForecastWeatherResponse
import com.example.weatherapp.util.WeatherInfoConstants.API_KEY
import com.example.weatherapp.util.WeatherInfoConstants.FORECAST_WEATHER_DAYS_COUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInfoApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = API_KEY,
    ): Response<CurrentWeatherResponse>

    //This is request of Forecast weather, we don't use this, we use fake data
    // (Api don't work without payment)
    @GET("data/2.5/forecast/daily")
    suspend fun getForecastWeather(
        @Query("q") cityName: String,
        @Query("cnt") cnt: Int = FORECAST_WEATHER_DAYS_COUNT,
        @Query("appid") appid: String = API_KEY,
    ): Response<ForecastWeatherResponse>
}
