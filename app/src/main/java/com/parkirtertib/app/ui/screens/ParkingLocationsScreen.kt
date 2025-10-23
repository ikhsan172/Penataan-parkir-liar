package com.parkirtertib.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingLocationsScreen(
    onNavigateToMap: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lokasi Parkir Resmi",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Cari lokasi parkir...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Statistics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("Total Lokasi", "12", Icons.Default.LocationOn)
            StatItem("Tersedia", "8", Icons.Default.CheckCircle)
            StatItem("Terisi", "4", Icons.Default.Warning)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // View on Map Button
        Button(
            onClick = onNavigateToMap,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Map, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Lihat di Peta")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Parking Locations List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getParkingLocations()) { location ->
                ParkingLocationCard(location = location)
            }
        }
    }
}

@Composable
fun StatItem(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ParkingLocationCard(location: ParkingLocationData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                AvailabilityChip(isAvailable = location.isAvailable)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location.operatingHours,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tarif: Rp ${location.hourlyRate}/jam",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${location.availableSpots} tersedia",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (location.availableSpots > 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AvailabilityChip(isAvailable: Boolean) {
    AssistChip(
        onClick = { },
        label = { Text(if (isAvailable) "Tersedia" else "Penuh") },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isAvailable) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
        )
    )
}

// Data classes
data class ParkingLocationData(
    val name: String,
    val address: String,
    val operatingHours: String,
    val hourlyRate: Int,
    val availableSpots: Int,
    val isAvailable: Boolean
)

// Sample data
fun getParkingLocations(): List<ParkingLocationData> = listOf(
    ParkingLocationData(
        "Parkir Thamrin",
        "Jl. Thamrin No. 1, Jakarta Pusat",
        "06:00 - 22:00",
        5000,
        15,
        true
    ),
    ParkingLocationData(
        "Parkir Sudirman",
        "Jl. Sudirman No. 25, Jakarta Selatan",
        "24 Jam",
        3000,
        0,
        false
    ),
    ParkingLocationData(
        "Parkir Gatot Subroto",
        "Jl. Gatot Subroto No. 10, Jakarta Selatan",
        "07:00 - 21:00",
        4000,
        8,
        true
    ),
    ParkingLocationData(
        "Parkir Senayan",
        "Jl. Asia Afrika No. 5, Jakarta Pusat",
        "08:00 - 20:00",
        6000,
        3,
        true
    )
)
