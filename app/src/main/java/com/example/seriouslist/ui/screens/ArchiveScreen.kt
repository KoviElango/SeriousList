package com.example.seriouslist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seriouslist.TaskViewModel

@Composable
fun ArchiveScreen(viewModel: TaskViewModel) {
    val archivedTasks by viewModel.archivedTasks.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(archivedTasks) { task ->
            TaskItem(task = task)
        }
    }
}
