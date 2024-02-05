package com.sharath070.nebulaforecast.domain.models.weather

data class WeatherResponse(
    val main: Main,
    val sunriseSunset: Sys,
    val weather: List<Weather>,
    val wind: Wind
)