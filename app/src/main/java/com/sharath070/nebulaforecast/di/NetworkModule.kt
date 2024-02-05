package com.sharath070.nebulaforecast.di

import com.sharath070.nebulaforecast.data.network.AutoCompleteApi
import com.sharath070.nebulaforecast.data.network.CurrentWeatherApi
import com.sharath070.nebulaforecast.data.network.GeoCoderApi
import com.sharath070.nebulaforecast.uitls.Constants.Companion.AUTOCOMPLETE_BASE_URL
import com.sharath070.nebulaforecast.uitls.Constants.Companion.GEO_BASE_URL
import com.sharath070.nebulaforecast.uitls.Constants.Companion.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGeoCoderApi(): GeoCoderApi {
        return Retrofit.Builder()
            .baseUrl(GEO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideCurrentWeather(): CurrentWeatherApi {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideAutoCompleteAddress(): AutoCompleteApi {
        return Retrofit.Builder()
            .baseUrl(AUTOCOMPLETE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}