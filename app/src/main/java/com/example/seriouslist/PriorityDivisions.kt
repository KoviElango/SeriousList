package com.example.seriouslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

val LightUrgentImportantColor = Color(0xFFFFCDD2)
val LightNotUrgentImportantColor = Color(0xFFBBDEFB)
val LightUrgentNotImportantColor = Color(0xFFFFE0B2)
val LightNotUrgentNotImportantColor = Color(0xFFC8E6C9)

@Composable
fun EisenhowerMatrixScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
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
                    modifier = Modifier.weight(1f)
                )
                PriorityBox(
                    color = NotUrgentImportantColor,
                    text = "Schedule",
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
                    modifier = Modifier.weight(1f)
                )
                PriorityBox(
                    color = NotUrgentNotImportantColor,
                    text = "Eliminate",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun PriorityBox(color: Color, text: String, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .zIndex(1f),
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

@Preview(showBackground = true)
@Composable
fun EisenhowerMatrixScreenPreview() {
    EisenhowerMatrixScreen()
}
