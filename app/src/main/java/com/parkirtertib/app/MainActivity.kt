package com.parkirtertib.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.parkirtertib.app.ui.screens.*
import com.parkirtertib.app.ui.theme.ParkirTertibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkirTertibTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                listOf(
                    Screen.Dashboard to Icons.Default.Dashboard,
                    Screen.ParkingLocations to Icons.Default.LocationOn,
                    Screen.Razia to Icons.Default.Notifications,
                    Screen.Payment to Icons.Default.Payment,
                    Screen.Camera to Icons.Default.CameraAlt
                ).forEach { (screen, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onNavigateToViolations = {
                        // Navigate to violations list
                    },
                    onNavigateToParkingLocations = {
                        navController.navigate(Screen.ParkingLocations.route)
                    },
                    onNavigateToRazia = {
                        navController.navigate(Screen.Razia.route)
                    },
                    onNavigateToPayment = {
                        navController.navigate(Screen.Payment.route)
                    }
                )
            }
            
            composable(Screen.ParkingLocations.route) {
                ParkingLocationsScreen(
                    onNavigateToMap = {
                        // Navigate to map view
                    }
                )
            }
            
            composable(Screen.Razia.route) {
                RaziaScreen(
                    onAddRazia = {
                        // Navigate to add razia form
                    }
                )
            }
            
            composable(Screen.Payment.route) {
                PaymentScreen(
                    onViolationClick = {
                        // Navigate to violation details
                    },
                    onPaymentClick = {
                        // Navigate to payment form
                    }
                )
            }
            
            composable(Screen.Camera.route) {
                CameraScreen(
                    onPhotoTaken = { photoPath ->
                        // Handle photo taken
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

sealed class Screen(val route: String, val title: String) {
    object Dashboard : Screen("dashboard", "Dashboard")
    object ParkingLocations : Screen("parking_locations", "Lokasi")
    object Razia : Screen("razia", "Razia")
    object Payment : Screen("payment", "Bayar")
    object Camera : Screen("camera", "Kamera")
}
