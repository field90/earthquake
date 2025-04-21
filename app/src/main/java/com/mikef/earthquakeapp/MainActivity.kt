package com.mikef.earthquakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mikef.earthquakeapp.ui.screens.EarthquakeSummaryScreen
import com.mikef.earthquakeapp.ui.theme.EarthquakeAppTheme
import com.mikef.earthquakeapp.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EarthquakeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EarthquakeSummaryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    val earthquakes by viewModel.earthquakes.collectAsState()

    if (earthquakes.isEmpty()) {
        Text(text = "Loading...", modifier = modifier)
    } else {
        Text(
            text = "First quake: ${earthquakes[0].properties?.place ?: "Unknown"}",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EarthquakeAppTheme {
        Greeting("Android")
    }
}