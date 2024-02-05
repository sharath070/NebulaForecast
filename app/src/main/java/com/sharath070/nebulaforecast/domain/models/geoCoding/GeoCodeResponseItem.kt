package com.sharath070.nebulaforecast.domain.models.geoCoding

data class GeoCodeResponseItem(
    val address: String,
    val area: String,
    val country: String,
    val house: String,
    val locality: String,
    val location_type: String,
    val neighborhood: String,
    val postal_code: String,
    val region: String,
    val street: String,
    val type: String
)