package com.sharath070.nebulaforecast.data.network

import com.sharath070.nebulaforecast.domain.models.weather.WeatherResponse
import com.sharath070.nebulaforecast.uitls.Constants.Companion.WEATHER_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): WeatherResponse

}