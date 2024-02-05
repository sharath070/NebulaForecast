package com.sharath070.nebulaforecast.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow


interface LocationTracker {
    fun getLocationUpdates(): Flow<Location>
    class LocationException(message: String): Exception()
}