package com.sharath070.nebulaforecast.data.network

import com.sharath070.nebulaforecast.domain.models.geoCoding.GeoCodeResponse
import com.sharath070.nebulaforecast.domain.models.geoCoding.GeoCodeResponseItem
import com.sharath070.nebulaforecast.uitls.Constants.Companion.GE0_API_KEY
import com.sharath070.nebulaforecast.uitls.Constants.Companion.GEO_API_HOST
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GeoCoderApi {

    @GET("/ReverseGeocode")
    @Headers(
        "X-RapidAPI-Key: $GE0_API_KEY",
        "X-RapidAPI-Host: $GEO_API_HOST"
    )
    suspend fun getLocality(
        @Query("location") location: String,
        @Query("language") language: String = "en"
    ): GeoCodeResponse

}