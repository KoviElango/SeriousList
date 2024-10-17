package com.example.seriouslist.ui.screens

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
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen(navController) == "quadrant",
            onClick = { navController.navigate("quadrant") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Quadrant View") },
            label = { Text("Quadrant") }
        )
        NavigationBarItem(
            selected = currentScreen(navController) == "list",
            onClick = { navController.navigate("list") },
            icon = { Icon(Icons.Default.List, contentDescription = "List View") },
            label = { Text("List") }
        )
        NavigationBarItem(
            selected = currentScreen(navController) == "archive",
            onClick = { navController.navigate("archive") },
            icon = { Icon(Icons.Default.Archive, contentDescription = "Archive View") },
            label = { Text("Archive") }
        )
    }
}
