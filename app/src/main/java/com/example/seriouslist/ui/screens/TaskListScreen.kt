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
fun TaskListScreen(viewModel: TaskViewModel, modifier: Modifier = Modifier) {

    val taskList by viewModel.tasks.collectAsState()


    LazyColumn(modifier = modifier) {
        items(taskList) { task ->
            Text(text = task.itemName)
        }
    }
}
