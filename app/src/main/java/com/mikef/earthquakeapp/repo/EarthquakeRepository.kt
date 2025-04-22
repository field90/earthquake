package com.mikef.earthquakeapp.repo

import com.mikef.earthquakeapp.api.EarthquakeApi
import com.mikef.earthquakeapp.data.EarthquakeResponse
import com.mikef.earthquakeapp.data.Features
import javax.inject.Inject

class EarthquakeRepository @Inject constructor(
    private val api: EarthquakeApi
) {
    suspend fun fetchEarthquakes(): EarthquakeResponse {
        return api.getRecentEarthquakes()
    }

    suspend fun fetchEarthquakeDetailFromUrl(url: String): Features {
        return api.getEarthquakeDetailFromUrl(url)
    }
}