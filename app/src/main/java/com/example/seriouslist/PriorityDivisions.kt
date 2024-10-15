package com.example.seriouslist

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

// Color scheme
val UrgentImportantColor = Color(0xFFD32F2F)
val NotUrgentImportantColor = Color(0xFF1976D2)
val UrgentNotImportantColor = Color(0xFFF57C00)
val NotUrgentNotImportantColor = Color(0xFF388E3C)

@Composable
fun EisenhowerMatrixScreen() {
    var expandedBox by remember { mutableStateOf<Int?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (expandedBox != null) {
            ExpandedBox(
                color = getBoxColor(expandedBox!!),
                text = getBoxText(expandedBox!!),
                onBoxClicked = { expandedBox = null }
            )
        } else {
            // Default 4-quadrant layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    PriorityBox(
                        color = UrgentImportantColor,
                        text = "Do First",
                        index = 1,
                        onBoxClicked = { expandedBox = it },
                        modifier = Modifier.weight(1f)
                    )
                    PriorityBox(
                        color = NotUrgentImportantColor,
                        text = "Schedule",
                        index = 2,
                        onBoxClicked = { expandedBox = it },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    PriorityBox(
                        color = UrgentNotImportantColor,
                        text = "Delegate",
                        index = 3,
                        onBoxClicked = { expandedBox = it },
                        modifier = Modifier.weight(1f)
                    )
                    PriorityBox(
                        color = NotUrgentNotImportantColor,
                        text = "Eliminate",
                        index = 4,
                        onBoxClicked = { expandedBox = it },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun PriorityBox(
    color: Color,
    text: String,
    index: Int,
    onBoxClicked: (Int) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .zIndex(1f)
            .clickable { onBoxClicked(index) },  // Handle clicks to expand
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun ExpandedBox(
    color: Color,
    text: String,
    onBoxClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            // Centered text
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 30.sp
            )

            // X button in the top-right corner
            IconButton(
                onClick = { onBoxClicked() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)  // Adjust padding to move it away from the edge
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,  // White color for better contrast
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

fun getBoxColor(index: Int): Color {
    return when (index) {
        1 -> UrgentImportantColor
        2 -> NotUrgentImportantColor
        3 -> UrgentNotImportantColor
        4 -> NotUrgentNotImportantColor
        else -> Color.Gray
    }
}

fun getBoxText(index: Int): String {
    return when (index) {
        1 -> "Do First"
        2 -> "Schedule"
        3 -> "Delegate"
        4 -> "Eliminate"
        else -> "Unknown"
    }
}

@Preview(showBackground = true)
@Composable
fun EisenhowerMatrixScreenPreview() {
    EisenhowerMatrixScreen()
}
