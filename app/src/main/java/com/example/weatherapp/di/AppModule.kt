package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.example.weatherapp.network.WeatherInfoApi
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.database.WeatherDAO
import com.example.weatherapp.util.WeatherInfoConstants.CURRENT_WEATHER_BASE_URL
import com.example.weatherapp.repo.WeatherInfoRepo
import com.example.weatherapp.repo.WeatherInfoRepoImpl
import com.example.weatherapp.ui.viewmodel.WeatherInfoViewModel
import com.example.weatherapp.usecase.WeatherInfoUseCase
import com.example.weatherapp.usecase.WeatherInfoUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(CURRENT_WEATHER_BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): WeatherInfoApi =
    retrofit.create(WeatherInfoApi::class.java)

private fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "database").build()
}

private fun provideDao(appDatabase: AppDatabase): WeatherDAO? {
    return appDatabase.weatherDAO()
}

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val weatherInfoModule = module {
    single<WeatherInfoRepo> {
        return@single WeatherInfoRepoImpl(get(), get())
    }

    single<WeatherInfoUseCase> {
        return@single WeatherInfoUseCaseImpl(get())
    }

    viewModel {
        WeatherInfoViewModel(get())
    }
}
