package com.mikef.earthquakeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mikef.earthquakeapp.data.Features

@Composable
fun EarthquakeRow(quake: Features, modifier: Modifier = Modifier) {
    val mag = quake.properties?.mag ?: 0.0
    val place = quake.properties?.place ?: "Unknown location"

    val bgColor = when (mag) {
        in 0.0..1.0 -> Color(0xFFB2FF59)
        in 1.0..3.0 -> Color(0xFFFFFF8D)
        in 3.0..5.0 -> Color(0xFFFFA726)
        in 5.0..7.0 -> Color(0xFFFF7043)
        else -> Color(0xFFD32F2F)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // TODO add Details Screen
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(bgColor)
                .padding(16.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text("Magnitude: %.1f".format(mag), style = MaterialTheme.typography.titleMedium)
                Text(place, style = MaterialTheme.typography.bodyMedium)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View Details",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
