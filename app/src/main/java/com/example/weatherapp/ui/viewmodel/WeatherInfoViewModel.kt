package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.DailyWeatherResponse
import com.example.weatherapp.data.ForecastWeatherResponse
import com.example.weatherapp.data.ResponseState
import com.example.weatherapp.database.MainModel
import com.example.weatherapp.usecase.WeatherInfoUseCase
import kotlinx.coroutines.launch

class WeatherInfoViewModel(
    private val weatherInfoUseCase: WeatherInfoUseCase,
) : ViewModel() {

    private var currentWeatherMutableLiveData =
        MutableLiveData<ResponseState<CurrentWeatherResponse>>()

    private var forecastWeatherMutableLiveData =
        MutableLiveData<DailyWeatherResponse>()

    fun getCurrentWeatherMutableLiveData(): LiveData<ResponseState<CurrentWeatherResponse>> =
        currentWeatherMutableLiveData

    fun getForecastWeatherMutableLiveData(): LiveData<DailyWeatherResponse> =
        forecastWeatherMutableLiveData

    fun getCurrentWeather(cityName: String) = viewModelScope.launch {
        try {
            currentWeatherMutableLiveData.value = ResponseState.Loading()
            val response = weatherInfoUseCase.getCurrentWeather(cityName)
            if (response.isSuccessful && response.body() != null) {
                currentWeatherMutableLiveData.value = ResponseState.Success(response.body())
            } else {
                currentWeatherMutableLiveData.value = ResponseState.Error()
            }
        } catch (ex: java.lang.Exception) {
            currentWeatherMutableLiveData.value = ResponseState.Error()
        }
    }

    fun getForecastWeather(cityName: String) = viewModelScope.launch {
        forecastWeatherMutableLiveData.postValue(weatherInfoUseCase.getForecastWeather(cityName))
    }

    fun saveWeather(mainModel: MainModel) = viewModelScope.launch {
        weatherInfoUseCase.saveWeather(mainModel)
    }

    fun clearData() = viewModelScope.launch {
        weatherInfoUseCase.clearData()
    }

    fun getSavedWeather() : LiveData<List<MainModel>> = weatherInfoUseCase.getSavedWeather()

}