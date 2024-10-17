package com.example.seriouslist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seriouslist.TaskViewModel
import android.util.Log

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    LaunchedEffect(tasks) {
        if (tasks.isEmpty()) {
            Log.d("TaskListScreen", "No tasks found")
        } else {
            Log.d("TaskListScreen", "Tasks found: ${tasks.size}")
        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task)
        }
    }
}

