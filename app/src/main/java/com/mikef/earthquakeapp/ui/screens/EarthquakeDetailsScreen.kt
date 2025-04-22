package com.mikef.earthquakeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.LatLng
import com.mikef.earthquakeapp.util.formatDate
import com.mikef.earthquakeapp.vm.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthquakeDetailScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController = rememberNavController() // ← pass from MainActivity ideally
) {
    val earthquake by viewModel.selectedQuake.collectAsState()


    val lat = earthquake?.geometry?.coordinates?.getOrNull(1) ?: 0.0
    val lon = earthquake?.geometry?.coordinates?.getOrNull(0) ?: 0.0
    val depth = earthquake?.geometry?.coordinates?.getOrNull(2)
    val place = earthquake?.properties?.place ?: "Unknown"
    val mag = earthquake?.properties?.mag ?: 0.0
    val time = earthquake?.properties?.time

    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
            LatLng(lat, lon), 6f
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Earthquake Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.selectEarthquake(null)
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            // Info card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Magnitude: %.2f".format(mag),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Location: $place", style = MaterialTheme.typography.bodyLarge)
                    depth?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Depth: %.2f km".format(it), style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Time: ${formatDate(time)}", style = MaterialTheme.typography.bodyMedium)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            GoogleMap(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
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
}
