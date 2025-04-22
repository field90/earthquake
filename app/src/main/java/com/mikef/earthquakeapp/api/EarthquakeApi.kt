package com.mikef.earthquakeapp.api

import com.mikef.earthquakeapp.data.EarthquakeResponse
import com.mikef.earthquakeapp.data.Features
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface EarthquakeApi {
    @GET("summary/all_hour.geojson")
    suspend fun getRecentEarthquakes(): EarthquakeResponse

    @GET
    suspend fun getEarthquakeDetailFromUrl(@Url fullUrl: String): Features}