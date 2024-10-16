package com.example.seriouslist.local_data_storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE isArchived = 0")
    fun getActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isArchived = 1 ORDER BY createdDate ASC")
    fun getArchivedTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE urgency = 1 AND importance = 1 AND isArchived = 0")
    fun getUrgentImportantTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE urgency = 0 AND importance = 1 AND isArchived = 0")
    fun getNotUrgentImportantTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE urgency = 1 AND importance = 0 AND isArchived = 0")
    fun getUrgentNotImportantTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE urgency = 0 AND importance = 0 AND isArchived = 0")
    fun getNotUrgentNotImportantTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isArchived = 0 ORDER BY createdDate ASC")
    fun getTasksSortedByCreatedDate(): Flow<List<TaskEntity>>
}

