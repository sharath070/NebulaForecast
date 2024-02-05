package com.sharath070.nebulaforecast.data.network

import com.sharath070.nebulaforecast.domain.models.addressAutoComplete.AutoCompleteResponse
import com.sharath070.nebulaforecast.uitls.Constants.Companion.AUTOCOMPLETE_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteApi {

    @GET("autocomplete")
    suspend fun autoCompleteAddress(
        @Query("text") text: String,
        @Query("format") format: String = "json",
        @Query("apiKey") apiKey: String = AUTOCOMPLETE_API_KEY
    ): AutoCompleteResponse

}