package com.example.seriouslist.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seriouslist.TaskViewModel

@Composable
fun NavigationComponent(
    navController: NavHostController,
    viewModel: TaskViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "quadrant"
    ) {
        composable("quadrant") {
            EisenhowerMatrixScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues))
        }
        composable("list") {
            TaskListScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues))
        }
        composable("archive") {
            ArchiveScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues))
        }
    }
}
