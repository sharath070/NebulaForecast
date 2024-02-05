package com.sharath070.nebulaforecast.data.repository

import android.util.Log
import com.sharath070.nebulaforecast.data.DataOrException
import com.sharath070.nebulaforecast.data.network.AutoCompleteApi
import com.sharath070.nebulaforecast.data.network.CurrentWeatherApi
import com.sharath070.nebulaforecast.data.network.GeoCoderApi
import com.sharath070.nebulaforecast.domain.models.addressAutoComplete.AddressList
import com.sharath070.nebulaforecast.domain.models.addressAutoComplete.AutoCompleteResponse
import com.sharath070.nebulaforecast.domain.models.geoCoding.GeoCodeResponse
import com.sharath070.nebulaforecast.domain.models.weather.WeatherResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val geoCoderApi: GeoCoderApi,
    private val weatherApi: CurrentWeatherApi,
    private val autoCompleteApi: AutoCompleteApi
) {

    suspend fun getLocality(latitude: String, longitude: String):
            DataOrException<GeoCodeResponse, Boolean, Exception>
    {
        Log.d("GeoCoder", "$latitude $longitude")

        val response = try {
            geoCoderApi.getLocality("$latitude%2C$longitude")
        } catch (e: Exception) {
            Log.d("GeoCoder", e.toString())
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun getWeather(latitude: String, longitude: String):
            DataOrException<WeatherResponse, Boolean, Exception>
    {
        val response = try {
            weatherApi.getCurrentWeather(latitude, longitude)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun getAddress(text: String):
            DataOrException<List<AddressList>, Boolean, Exception>
    {
        val response = try {
            autoCompleteApi.autoCompleteAddress(text)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response.results)
    }

}