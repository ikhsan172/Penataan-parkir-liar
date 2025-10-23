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
fun RaziaScreen(
    onAddRazia: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Semua", "Aktif", "Selesai")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Operasi Razia",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Add Razia Button
        Button(
            onClick = onAddRazia,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tambah Razia")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Statistics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem("Total Razia", "15", Icons.Default.Notifications)
            StatItem("Aktif", "3", Icons.Default.PlayArrow)
            StatItem("Selesai", "12", Icons.Default.CheckCircle)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tabs
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Razia List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val raziaList = getRaziaList().filter { razia ->
                when (selectedTab) {
                    0 -> true // All
                    1 -> razia.isActive // Active
                    2 -> !razia.isActive // Completed
                    else -> true
                }
            }
            
            if (raziaList.isEmpty()) {
                item {
                    EmptyStateCard()
                }
            } else {
                items(raziaList) { razia ->
                    RaziaCard(razia = razia)
                }
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
fun EmptyStateCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.NotificationsOff,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tidak ada notifikasi razia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ketika ada operasi razia, notifikasi akan muncul di sini",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun RaziaCard(razia: RaziaData) {
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
                    text = razia.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                StatusChip(isActive = razia.isActive)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = razia.schedule,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
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
                    text = razia.area,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            if (razia.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = razia.description,
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
                    text = "Area Terdampak: ${razia.affectedAreas}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Petugas: ${razia.officers}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun StatusChip(isActive: Boolean) {
    AssistChip(
        onClick = { },
        label = { Text(if (isActive) "Aktif" else "Selesai") },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isActive) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )
    )
}

// Data classes
data class RaziaData(
    val title: String,
    val schedule: String,
    val area: String,
    val description: String,
    val affectedAreas: Int,
    val officers: Int,
    val isActive: Boolean
)

// Sample data
fun getRaziaList(): List<RaziaData> = listOf(
    RaziaData(
        "Razia Parkir Liar - Thamrin",
        "25 Jan 2024, 08:00 - 12:00",
        "Jl. Thamrin, Jakarta Pusat",
        "Operasi penertiban parkir liar di sepanjang Jl. Thamrin",
        5,
        8,
        true
    ),
    RaziaData(
        "Razia Parkir Liar - Sudirman",
        "24 Jan 2024, 14:00 - 18:00",
        "Jl. Sudirman, Jakarta Selatan",
        "Penertiban parkir liar di area Sudirman",
        3,
        6,
        false
    ),
    RaziaData(
        "Razia Parkir Liar - Gatot Subroto",
        "23 Jan 2024, 10:00 - 16:00",
        "Jl. Gatot Subroto, Jakarta Selatan",
        "Operasi gabungan penertiban parkir liar",
        7,
        12,
        false
    ),
    RaziaData(
        "Razia Parkir Liar - Senayan",
        "26 Jan 2024, 09:00 - 15:00",
        "Jl. Asia Afrika, Jakarta Pusat",
        "Penertiban parkir liar di area Senayan",
        4,
        10,
        true
    )
)
