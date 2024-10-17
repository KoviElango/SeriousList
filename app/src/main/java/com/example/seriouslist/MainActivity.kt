package com.example.seriouslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.room.Room
import com.example.seriouslist.ui.theme.SeriousListTheme
import com.example.seriouslist.local_data_storage.TaskDatabase

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels {
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task-database"
        ).build()

        TaskViewModelFactory(db.taskDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeriousListTheme {
                EisenhowerMatrixScreen(viewModel = taskViewModel)
            }
        }
    }
}


