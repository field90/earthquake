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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mikef.earthquakeapp.data.Features
import com.mikef.earthquakeapp.data.Geometry
import com.mikef.earthquakeapp.data.Properties
import com.mikef.earthquakeapp.vm.MainViewModel

@Composable
fun EarthquakeRow(
    quake: Features, modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel(),
) {
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
                quake.properties?.detail?.let { detailUrl ->
                    viewModel.selectQuakeByDetailUrl(detailUrl)
                }            },
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

@Preview(showBackground = true)
@Composable
fun EarthquakeRowPreview() {


    val sampleQuakeGreen = Features(
        id = "earthquake-1preview",
        properties = Properties(
            mag = 0.5,
            place = "Maui, Hawaii",
            time = System.currentTimeMillis(),
            url = "",
            title = "M 1.5 - Maui, Hawaii",
        ),
        geometry = Geometry(
            type = "Point",
            coordinates = arrayListOf(-122.3, 37.8, 10.0)
        )
    )

    val sampleQuakeYellow = Features(
        id = "earthquake-1preview",
        properties = Properties(
            mag = 1.5,
            place = "Maui, Hawaii",
            time = System.currentTimeMillis(),
            url = "",
            title = "M 1.5 - Maui, Hawaii",
        ),
        geometry = Geometry(
            type = "Point",
            coordinates = arrayListOf(-122.3, 37.8, 10.0)
        )
    )

    val sampleQuakeOrange = Features(
        id = "earthquake-1preview",
        properties = Properties(
            mag = 4.0,
            place = "Maui, Hawaii",
            time = System.currentTimeMillis(),
            url = "",
            title = "M 1.5 - Maui, Hawaii",
        ),
        geometry = Geometry(
            type = "Point",
            coordinates = arrayListOf(-122.3, 37.8, 10.0)
        )
    )

    val sampleQuakeHeavyOrange = Features(
        id = "earthquake-1preview",
        properties = Properties(
            mag = 6.0,
            place = "Maui, Hawaii",
            time = System.currentTimeMillis(),
            url = "",
            title = "M 1.5 - Maui, Hawaii",
        ),
        geometry = Geometry(
            type = "Point",
            coordinates = arrayListOf(-122.3, 37.8, 10.0)
        )
    )
    val sampleQuakeRed = Features(
        id = "earthquake-1preview",
        properties = Properties(
            mag = 8.5,
            place = "Maui, Hawaii",
            time = System.currentTimeMillis(),
            url = "",
            title = "M 8.5 - Maui, Hawaii",
        ),
        geometry = Geometry(
            type = "Point",
            coordinates = arrayListOf(-122.3, 37.8, 10.0)
        )
    )

    Column {
        /*   EarthquakeRow(quake = sampleQuakeGreen)
           EarthquakeRow(quake = sampleQuakeYellow)
           EarthquakeRow(quake = sampleQuakeOrange)
           EarthquakeRow(quake = sampleQuakeHeavyOrange)
           EarthquakeRow(quake = sampleQuakeRed)*/
    }
}
