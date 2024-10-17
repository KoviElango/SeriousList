package com.example.seriouslist.local_data_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(DateConverter::class)  // Add this line
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
