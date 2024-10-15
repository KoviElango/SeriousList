package com.example.todolist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color scheme
val UrgentImportantColor = Color(0xFFD32F2F)
val NotUrgentImportantColor = Color(0xFF1976D2)
val UrgentNotImportantColor = Color(0xFFF57C00)
val NotUrgentNotImportantColor = Color(0xFF388E3C)

@Composable
fun ToDoListScreen() {
    // Sample tasks with priorities
    val tasks = remember {
        mutableStateListOf(
            Task("Finish project report", true, UrgentImportantColor),
            Task("Plan next week’s schedule", true, NotUrgentImportantColor),
            Task("Delegate project tasks", false, UrgentNotImportantColor),
            Task("Clear old emails", false, NotUrgentNotImportantColor),
            Task("Prepare presentation", true, UrgentImportantColor),
            Task("Clean desk", false, NotUrgentNotImportantColor)
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "To-Do List",
                style = MaterialTheme.typography.headlineMedium,  // Use 'style' instead of 'textStyle'
                modifier = Modifier.padding(bottom = 16.dp)
            )

            tasks.forEachIndexed { index, task ->
                TaskItem(
                    task = task,
                    onTaskChecked = { isChecked ->
                        tasks[index] = tasks[index].copy(isCompleted = isChecked)
                    }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: (Boolean) -> Unit
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Ensure this is applied to the card
        colors = CardDefaults.cardColors(containerColor = task.color)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onTaskChecked,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
        }
    }
}

data class Task(
    val title: String,
    val isCompleted: Boolean,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen()
}
