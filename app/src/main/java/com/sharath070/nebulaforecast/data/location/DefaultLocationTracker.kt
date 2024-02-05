package com.sharath070.nebulaforecast.data.location

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.sharath070.nebulaforecast.domain.location.LocationTracker
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DefaultLocationTracker(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override fun getLocationUpdates(): Flow<Location> {
        return callbackFlow {

            Log.d("@@@@", "getLocationUpdates")

            val hasFineLocationPermission = ContextCompat.checkSelfPermission(
                application,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
                application,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            val locationManager = application.getSystemService(
                Context.LOCATION_SERVICE
            ) as LocationManager

            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                    || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (!isGpsEnabled && !(hasCoarseLocationPermission || hasFineLocationPermission)) {
                Log.e("@@@@", "Permission not given or gps not enabled")
                throw LocationTracker.LocationException("Permission not given or gps not enabled")
            }

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    locationResult.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }
            val locationRequest = LocationRequest.Builder(1000).build()

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            }
        }
    }

}







/*val hasFineLocationPermission = ContextCompat.checkSelfPermission(
               application,
               android.Manifest.permission.ACCESS_FINE_LOCATION
           ) == PackageManager.PERMISSION_GRANTED

           val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
               application,
               android.Manifest.permission.ACCESS_COARSE_LOCATION
           ) == PackageManager.PERMISSION_GRANTED

           val locationManager = application.getSystemService(
               Context.LOCATION_SERVICE
           ) as LocationManager

           val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                   || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

           if (!isGpsEnabled && !(hasCoarseLocationPermission || hasFineLocationPermission)) {
               Log.e("@@@@", "Permission not given or gps not enabled")
               throw Exception("Permission not given or gps not enabled")
           }
       }

        return suspendCancellableCoroutine { cont ->
           Log.d(
               "@@@@",
               "Entered DefaultLocationTracker -> getCurrentLocation -> suspendCancellableCoroutine"
           )
           fusedLocationProviderClient.lastLocation.apply {
               if (isComplete) {
                   if (isSuccessful) {
                       Log.d(
                           "@@@@",
                           "Entered DefaultLocationTracker -> getCurrentLocation -> suspendCancellableCoroutine -> isSuccessful"
                       )
                       cont.resume(result)
                   } else {
                       Log.d("@@@@", "getCurrentLocation: isComplete && Task failed")
                       cont.resume(null)
                   }
                   return@suspendCancellableCoroutine
               }
               addOnSuccessListener {
                   Log.d(
                       "@@@@",
                       "Entered DefaultLocationTracker -> getCurrentLocation -> suspendCancellableCoroutine -> addOnSuccessListener"
                   )
                   if (it != null) {
                       cont.resume(it)
                   } else {
                       val location: Location? = locationRequest()
                       cont.resume(location)
                   }
               }
               addOnFailureListener {
                   Log.d("@@@@", "getCurrentLocation: addOnFailureListener")
                   cont.resume(null) // Resume coroutine with null location result
               }
               addOnCanceledListener {
                   Log.d(
                       "@@@@",
                       "Entered DefaultLocationTracker -> getCurrentLocation -> suspendCancellableCoroutine -> addOnCanceledListener"
                   )
                   cont.cancel()
               }
           }
       } */

/*@SuppressLint("MissingPermission")
private fun locationRequest(): Location? {
    var location: Location? = null
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            location = locationResult.lastLocation
        }
    }
    val locationRequest = LocationRequest.Builder(10000).build()

    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    return location
}*/