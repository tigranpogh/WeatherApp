package com.example.weatherapp.data

sealed class ResponseState<T> (val data: T? = null) {


    class Loading<T>(data: T? = null) : ResponseState<T>(data)

    class Error<T>(data: T? = null) : ResponseState<T>(data)

    class Success<T>(data: T?) : ResponseState<T>(data)
}
