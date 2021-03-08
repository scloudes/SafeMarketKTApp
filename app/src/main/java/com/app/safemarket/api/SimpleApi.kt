package com.app.safemarket.api

import com.app.safemarket.model.Marker
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface SimpleApi {
    @GET(" ")
    suspend fun getMarker(): Marker

    @PUT(" ")
    suspend fun updateMarker(
        @Body marker: Marker
    ):Response<Marker>
}