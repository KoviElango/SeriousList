package com.example.seriouslist

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

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
fun EisenhowerMatrixScreen() {
    var expandedQuadrant by remember { mutableStateOf<Quadrant?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (expandedQuadrant != null) {
            ExpandedQuadrant(
                quadrant = expandedQuadrant!!,
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
 * Displays an expanded view of a quadrant.
 *
 * @param quadrant The quadrant to display in expanded view.
 * @param onClose Callback for when the expanded view is closed.
 */
@Composable
fun ExpandedQuadrant(
    quadrant: Quadrant,
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
            Text(
                text = quadrant.text,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 30.sp
            )
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

@Preview(showBackground = true)
@Composable
fun EisenhowerMatrixScreenPreview() {
    EisenhowerMatrixScreen()
}