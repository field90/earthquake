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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mikef.earthquakeapp.data.Features
import com.mikef.earthquakeapp.ui.screens.EarthquakeDetailScreen
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
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: MainViewModel = hiltViewModel() // <-- Activity-scoped
                    NavHost(
                        navController = navController,
                        startDestination = "summary",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("summary") {
                            EarthquakeSummaryScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController,
                                viewModel = viewModel
                            )
                        }

                        composable("detail") {
                                EarthquakeDetailScreen(
                                    viewModel = viewModel,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
