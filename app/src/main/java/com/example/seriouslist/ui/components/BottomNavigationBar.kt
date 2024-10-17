package com.example.seriouslist.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "quadrant",
            onClick = { navController.navigate("quadrant") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Quadrant View") },
            label = { Text("Quadrant") }
        )
        NavigationBarItem(
            selected = currentRoute == "list",
            onClick = { navController.navigate("list") },
            icon = { Icon(Icons.Default.List, contentDescription = "List View") },
            label = { Text("List") }
        )
        NavigationBarItem(
            selected = currentRoute == "archive",
            onClick = { navController.navigate("archive") },
            icon = { Icon(Icons.Default.Archive, contentDescription = "Archive View") },
            label = { Text("Archive") }
        )
    }
}
