package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: Any? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("timezone")
	val timezone: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("population")
	val population: Int? = null
)

data class FeelsLike(

	@field:SerializedName("eve")
	val eve: Any? = null,

	@field:SerializedName("night")
	val night: Any? = null,

	@field:SerializedName("day")
	val day: Any? = null,

	@field:SerializedName("morn")
	val morn: Any? = null
)

data class ListItem(

	@field:SerializedName("rain")
	val rain: Any? = null,

	@field:SerializedName("sunrise")
	val sunrise: Int? = null,

	@field:SerializedName("temp")
	val temp: Temp? = null,

	@field:SerializedName("deg")
	val deg: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("clouds")
	val clouds: Int? = null,

	@field:SerializedName("feels_like")
	val feelsLike: FeelsLike? = null,

	@field:SerializedName("speed")
	val speed: Any? = null,

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("pop")
	val pop: Any? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("gust")
	val gust: Any? = null
)

data class Temp(

	@field:SerializedName("min")
	val min: Any? = null,

	@field:SerializedName("max")
	val max: Any? = null,

	@field:SerializedName("eve")
	val eve: Any? = null,

	@field:SerializedName("night")
	val night: Any? = null,

	@field:SerializedName("day")
	val day: Any? = null,

	@field:SerializedName("morn")
	val morn: Any? = null
)
