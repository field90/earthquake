package com.mikef.earthquakeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mikef.earthquakeapp.vm.MainViewModel
import com.mikef.earthquakeapp.ui.composables.EarthquakeRow
@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Recent Earthquakes")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.refreshEarthquakes()
                    }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (loading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(earthquakes) { quake ->
                    EarthquakeRow(quake, viewModel = viewModel)
                }
            }
        }
    }
}
