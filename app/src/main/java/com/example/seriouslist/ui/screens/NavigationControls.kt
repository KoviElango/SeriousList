package com.example.seriouslist.ui.screens

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.seriouslist.TaskViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    // Scaffold with FAB and Navigation
    Scaffold(
        floatingActionButton = {
            if (currentScreen(navController) != "archive") {
                FloatingActionButton(onClick = { /* Open Add Task Dialog */ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationComponent(navController = navController, viewModel = viewModel, paddingValues)
    }
}

fun currentScreen(navController: NavHostController): String? {
    return navController.currentBackStackEntry?.destination?.route
}