package com.example.weatherapp.data

data class DailyWeatherResponse(
    val days: List<String>? = listOf("07.04.2023", "08.04.2023", "09.04.2023", "10.04.2023"),
    val temp: List<String>? = listOf("25°C", "26°C", "25°C", "28°C")
)

