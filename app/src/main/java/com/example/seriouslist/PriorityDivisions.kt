package com.example.seriouslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrioriryScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Red)
            ){
                Text(text = "1",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Yellow)
            ){
                Text(text = "2",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green)
            ){
                Text(text = "3",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Magenta)
            ){
                Text(text = "4",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrioriryScreenPreview(){
    PrioriryScreen()
}