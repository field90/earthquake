package com.mikef.earthquakeapp.api

import com.mikef.earthquakeapp.data.EarthquakeResponse
import retrofit2.http.GET

interface EarthquakeApi {
    @GET("summary/all_hour.geojson")
    suspend fun getRecentEarthquakes(): EarthquakeResponse
}