package com.example.seriouslist.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.seriouslist.TaskViewModel

@Composable
fun ArchiveScreen(viewModel: TaskViewModel, modifier: Modifier = Modifier) {
    val archivedTasks by viewModel.archivedTasks.collectAsState()

    LazyColumn(modifier = modifier) {
        items(archivedTasks) { task ->
            Text(text = task.itemName)  // Display the archived task name
        }
    }
}
