package com.sharath070.nebulaforecast.presentation.screens.main

import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.nebulaforecast.data.repository.Repository
import com.sharath070.nebulaforecast.domain.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val repository: Repository
) : ViewModel() {

    private val _locationState = MutableStateFlow<Location?>(null)
    val locationState = _locationState.asStateFlow()

    private val _locality = MutableStateFlow("")
    val locality = _locality.asStateFlow()

    private val _weather = MutableStateFlow("")
    val weather = _weather.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            retrieveCoordinates()
            val deferredLocality = async{
                repository.remote.getLocality(
                    locationState.value?.latitude.toString(),
                    locationState.value?.longitude.toString()
                )
            }
            val deferredWeather = async {
                repository.remote.getWeather(
                    locationState.value?.latitude.toString(),
                    locationState.value?.longitude.toString()
                )
            }
            _locality.value = deferredLocality.await().data?.results?.first()?.locality.toString()
            _weather.value = deferredWeather.await().data?.main?.temp.toString()
        }
    }

    private suspend fun retrieveCoordinates() {
        locationTracker.getLocationUpdates()
            .catch { e -> e.printStackTrace() }
            .take(1)
            .collect { location ->
                Log.d("@@@@", "${location.latitude} ${location.longitude}")
                _locationState.value = location
            }
    }
}