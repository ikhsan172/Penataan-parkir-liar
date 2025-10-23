package com.parkirtertib.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToViolations: () -> Unit,
    onNavigateToParkingLocations: () -> Unit,
    onNavigateToRazia: () -> Unit,
    onNavigateToPayment: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Dashboard Penegakan",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        item {
            // Statistics Cards
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(getDashboardStats()) { stat ->
                    StatCard(
                        title = stat.title,
                        value = stat.value,
                        icon = stat.icon,
                        color = stat.color
                    )
                }
            }
        }
        
        item {
            // Quick Actions
            Text(
                text = "Aksi Cepat",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(getQuickActions()) { action ->
                    QuickActionCard(
                        title = action.title,
                        icon = action.icon,
                        onClick = action.onClick
                    )
                }
            }
        }
        
        item {
            // Recent Violations
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pelanggaran Terbaru",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                TextButton(onClick = onNavigateToViolations) {
                    Text("Lihat Semua")
                }
            }
        }
        
        items(getRecentViolations()) { violation ->
            ViolationCard(violation = violation)
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color
) {
    Card(
        modifier = Modifier.width(140.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.width(100.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun ViolationCard(violation: ViolationData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = violation.licensePlate,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = violation.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = violation.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "Rp ${violation.fine}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

// Data classes
data class StatData(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val color: Color
)

data class QuickActionData(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

data class ViolationData(
    val licensePlate: String,
    val location: String,
    val time: String,
    val fine: Int
)

// Sample data
fun getDashboardStats(): List<StatData> = listOf(
    StatData("Total Pelanggaran", "156", Icons.Default.Warning, MaterialTheme.colorScheme.error),
    StatData("Belum Dibayar", "23", Icons.Default.Payment, MaterialTheme.colorScheme.primary),
    StatData("Lokasi Resmi", "12", Icons.Default.LocationOn, MaterialTheme.colorScheme.tertiary),
    StatData("Razia Aktif", "3", Icons.Default.Notifications, MaterialTheme.colorScheme.secondary)
)

fun getQuickActions(): List<QuickActionData> = listOf(
    QuickActionData("Lokasi", Icons.Default.LocationOn) { },
    QuickActionData("Razia", Icons.Default.Notifications) { },
    QuickActionData("Bayar", Icons.Default.Payment) { },
    QuickActionData("Kamera", Icons.Default.CameraAlt) { }
)

fun getRecentViolations(): List<ViolationData> = listOf(
    ViolationData("B 1234 ABC", "Jl. Sudirman No. 15", "10:30 WIB", 50000),
    ViolationData("B 5678 DEF", "Jl. Thamrin No. 8", "09:15 WIB", 75000),
    ViolationData("B 9012 GHI", "Jl. Gatot Subroto No. 22", "08:45 WIB", 100000)
)
