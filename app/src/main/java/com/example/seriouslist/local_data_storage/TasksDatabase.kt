package com.example.seriouslist.local_data_storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TasksDatabase: RoomDatabase() {
    abstract val dao: TaskDao
}