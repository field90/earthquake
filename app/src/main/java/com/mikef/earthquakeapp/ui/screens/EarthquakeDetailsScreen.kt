package com.mikef.earthquakeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.*
import com.mikef.earthquakeapp.data.Features
import com.google.android.gms.maps.model.LatLng
import com.mikef.earthquakeapp.util.formatDate
import com.mikef.earthquakeapp.vm.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EarthquakeDetailScreen(viewModel: MainViewModel = hiltViewModel(),) {

    val earthquake by viewModel.selectedQuake.collectAsState()

    if (earthquake == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No earthquake selected.")
        }
        return
    }
    // Extracting data from the earthquake object
    val lat = earthquake?.geometry?.coordinates?.getOrNull(1) ?: 0.0
    val lon = earthquake?.geometry?.coordinates?.getOrNull(0) ?: 0.0
    val depth = earthquake?.geometry?.coordinates?.getOrNull(2)
    val place = earthquake?.properties?.place ?: "Unknown"
    val mag = earthquake?.properties?.mag ?: 0.0
    val time = earthquake?.properties?.time

    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(LatLng(lat, lon), 6f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Text Info Section
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text("Magnitude: $mag", style = MaterialTheme.typography.titleLarge)
            Text("Location: $place", style = MaterialTheme.typography.bodyLarge)
            if (depth != null) {
                Text("Depth: $depth km")
            }
            Text("Time: ${formatDate(time)}")
        }

        // Map Section
        GoogleMap(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = LatLng(lat, lon)),
                title = "Epicenter",
                snippet = place
            )
        }
    }
}


