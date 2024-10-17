package com.example.seriouslist.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.seriouslist.TaskViewModel
import com.example.seriouslist.ui.components.AddTaskDialog
import com.example.seriouslist.ui.components.BottomNavigationBar
import com.example.seriouslist.ui.components.NavigationComponent
import com.example.seriouslist.TaskEvent

@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    var showAddTaskDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
                FloatingActionButton(onClick = { showAddTaskDialog = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")

            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavigationComponent(navController = navController, viewModel = viewModel, paddingValues = paddingValues)

        if (showAddTaskDialog) {
            AddTaskDialog(
                onDismiss = { showAddTaskDialog = false },
                onSave = { taskName, urgency, importance, deadline ->
                    viewModel.onEvent(TaskEvent.SetTaskName(taskName))
                    viewModel.onEvent(TaskEvent.SetUrgency(urgency))
                    viewModel.onEvent(TaskEvent.SetImportance(importance))
                    viewModel.onEvent(TaskEvent.SetDeadline(deadline))
                    viewModel.onEvent(TaskEvent.SaveTask)
                    showAddTaskDialog = false
                }
            )
        }
    }
}
