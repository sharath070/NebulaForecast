package com.sharath070.nebulaforecast.domain.models.addressAutoComplete

data class AddressList(
    val address_line1: String,
    val address_line2: String,
    val city: String,
    val country: String,
    val country_code: String,
    val county: String,
    val formatted: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val place_id: String,
    val plus_code: String,
    val result_type: String,
    val state: String,
    val state_code: String,
    val timezone: Timezone
)