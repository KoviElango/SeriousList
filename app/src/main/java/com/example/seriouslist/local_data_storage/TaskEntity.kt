package com.example.seriouslist.local_data_storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val listItem: String,
    val isCompleted: Boolean,
    val urgency: Boolean,
    val importance: Boolean,
    val createdDate: Date,
    val completedDate: Date?,
)
