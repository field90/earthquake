package com.mikef.earthquakeapp.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikef.earthquakeapp.data.Features
import com.mikef.earthquakeapp.repo.EarthquakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EarthquakeRepository
) : ViewModel() {

    private val _earthquakes = MutableStateFlow<List<Features>>(emptyList())
    val earthquakes: StateFlow<List<Features>> = _earthquakes

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _selectedQuake = MutableStateFlow<Features?>(null)
    val selectedQuake: StateFlow<Features?> = _selectedQuake

    init {
        refreshEarthquakes()
    }

    fun refreshEarthquakes() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = repository.fetchEarthquakes()
                _earthquakes.value = response.features
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching data: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
    fun selectEarthquake(quake: Features?) {
        _selectedQuake.value = quake
    }

    fun selectQuakeByDetailUrl(url: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val quake = repository.fetchEarthquakeDetailFromUrl(url)
                _selectedQuake.value = quake
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failed to load quake detail: $e")
            } finally {
                _loading.value = false
            }
        }
    }
}
