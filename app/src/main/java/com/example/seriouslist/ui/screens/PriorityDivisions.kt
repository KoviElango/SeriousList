package com.example.seriouslist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.seriouslist.TaskViewModel
import com.example.seriouslist.local_data_storage.TaskEntity

/**
 * Represents a quadrant in the Eisenhower Matrix.
 *
 * @property color The background color of the quadrant.
 * @property text The text to display in the quadrant.
 */
data class Quadrant(val color: Color, val text: String)

/**
 * Defines the color scheme and quadrants for the Eisenhower Matrix.
 */
object EisenhowerMatrix {
    val quadrants = listOf(
        Quadrant(Color(0xFFD32F2F), "Do First"),
        Quadrant(Color(0xFF1976D2), "Schedule"),
        Quadrant(Color(0xFFF57C00), "Delegate"),
        Quadrant(Color(0xFF388E3C), "Eliminate")
    )
}

/**
 * Composable function that displays the Eisenhower Matrix screen.
 */
@Composable
fun EisenhowerMatrixScreen(viewModel: TaskViewModel, modifier: Modifier = Modifier) {
    val tasks by viewModel.tasks.collectAsState()
    var expandedQuadrant by remember { mutableStateOf<Quadrant?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (expandedQuadrant != null) {
            val filteredTasks = filterTasksForQuadrant(expandedQuadrant!!, tasks)
            ExpandedQuadrant(
                quadrant = expandedQuadrant!!,
                tasks = filteredTasks,
                onClose = { expandedQuadrant = null }
            )
        } else {
            QuadrantGrid(
                quadrants = EisenhowerMatrix.quadrants,
                onQuadrantClick = { expandedQuadrant = it }
            )
        }
    }
}

/**
 * Filter tasks based on the quadrant type (urgency and importance).
 */

fun filterTasksForQuadrant(quadrant: Quadrant, tasks: List<TaskEntity>): List<TaskEntity> {
    return when (quadrant.text) {
        "Do First" -> tasks.filter { it.urgency && it.importance }
        "Schedule" -> tasks.filter { !it.urgency && it.importance }
        "Delegate" -> tasks.filter { it.urgency && !it.importance }
        "Eliminate" -> tasks.filter { !it.urgency && !it.importance }
        else -> emptyList()
    }
}
/**
 * Displays a grid of quadrants.
 *
 * @param quadrants List of quadrants to display.
 * @param onQuadrantClick Callback for when a quadrant is clicked.
 */
@Composable
fun QuadrantGrid(
    quadrants: List<Quadrant>,
    onQuadrantClick: (Quadrant) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (i in 0..1) {
            Row(modifier = Modifier.weight(1f)) {
                for (j in 0..1) {
                    val index = i * 2 + j
                    if (index < quadrants.size) {
                        QuadrantBox(
                            quadrant = quadrants[index],
                            onClick = { onQuadrantClick(quadrants[index]) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Displays a single quadrant box.
 *
 * @param quadrant The quadrant to display.
 * @param onClick Callback for when the quadrant is clicked.
 * @param modifier Modifier for the quadrant box.
 */
@Composable
fun QuadrantBox(
    quadrant: Quadrant,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(3.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(quadrant.color)
        ) {
            Text(
                text = quadrant.text,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}

/**
 * Displays an expanded view of a quadrant with the list of tasks.
 *
 * @param quadrant The quadrant to display in expanded view.
 * @param tasks The list of tasks associated with the quadrant.
 * @param onClose Callback for when the expanded view is closed.
 */

@Composable
fun ExpandedQuadrant(
    quadrant: Quadrant,
    tasks: List<TaskEntity>,
    onClose: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(quadrant.color)
        ) {
            Column {
                Text(
                    text = quadrant.text,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    color = Color.White,
                    fontSize = 30.sp
                )
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(tasks) { task ->
                        TaskItem(task)
                    }
                }
            }
            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

/**
 * Displays a task item.
 *
 * @param task The task to display.
 */
@Composable
fun TaskItem(task: TaskEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.itemName, fontSize = 20.sp, color = Color.Black)
            Text(text = "Deadline: ${task.deadline}", fontSize = 14.sp, color = Color.Gray)
        }
    }
}
