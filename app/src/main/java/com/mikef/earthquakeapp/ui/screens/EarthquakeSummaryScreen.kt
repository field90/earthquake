package com.mikef.earthquakeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mikef.earthquakeapp.vm.MainViewModel
import com.mikef.earthquakeapp.ui.composables.EarthquakeRow

@Composable
fun EarthquakeSummaryScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController

) {
    val earthquakes by viewModel.earthquakes.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val selected by viewModel.selectedQuake.collectAsState()

    LaunchedEffect(selected) {
        if (selected != null) {
            navController.navigate("detail")
        }
    }

    Text("LOADED") // <--- just to confirm it's drawing at all

    if (loading) {
        Box(modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(earthquakes) { quake ->
                EarthquakeRow(quake, viewModel = viewModel)
            }
        }
    }
}
