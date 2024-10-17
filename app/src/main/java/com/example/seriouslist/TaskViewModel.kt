package com.example.seriouslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriouslist.local_data_storage.TaskDao
import com.example.seriouslist.local_data_storage.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao) : ViewModel() {

    // Mutable state to hold the list of tasks
    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    // Mutable state to hold the current task being created or updated
    private val _taskState = MutableStateFlow(TaskState())
    val taskState: StateFlow<TaskState> = _taskState

    // Mutable state to hold archived tasks
    private val _archivedTasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val archivedTasks: StateFlow<List<TaskEntity>> = _archivedTasks

    // Function to handle task-related events
    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.ArchiveTask -> {
                viewModelScope.launch {
                    val archivedTask = event.task.copy(isArchived = true)
                    dao.insertTask(archivedTask)
                }
            }
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    dao.deleteTask(event.task)
                }
            }
            TaskEvent.GetTasksSortedByCreatedDate -> {
                viewModelScope.launch {
                    dao.getTasksSortedByCreatedDate().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
            TaskEvent.GetUrgentImportantTasks -> {
                viewModelScope.launch {
                    dao.getUrgentImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
            TaskEvent.GetNotUrgentImportantTasks -> {
                viewModelScope.launch {
                    dao.getNotUrgentImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
            TaskEvent.GetUrgentNotImportantTasks -> {
                viewModelScope.launch {
                    dao.getUrgentNotImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
            TaskEvent.GetNotUrgentNotImportantTasks -> {
                viewModelScope.launch {
                    dao.getNotUrgentNotImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
            TaskEvent.SaveTask -> {
                viewModelScope.launch {
                    val task = TaskEntity(
                        itemName = _taskState.value.taskName,
                        deadline = _taskState.value.deadline,
                        importance = _taskState.value.importance,
                        urgency = _taskState.value.urgency,
                        isCompleted = false,
                        createdDate = java.util.Date(),
                        completedDate = null,
                        isArchived = false
                    )
                    Log.d("TaskViewModel", "Task inserted: $task")
                    dao.insertTask(task)

                }
            }

            is TaskEvent.SortTasks -> {
                // Handle sorting based on SortType
                sortTasks(event.sortType)
            }
            is TaskEvent.UpdateTask -> {
                viewModelScope.launch {
                    dao.insertTask(event.task)
                }
            }
            is TaskEvent.SetTaskName -> {
                _taskState.value = _taskState.value.copy(taskName = event.itemName)
            }
            is TaskEvent.SetDeadline -> {
                _taskState.value = _taskState.value.copy(deadline = event.deadline)
            }
            is TaskEvent.SetImportance -> {
                _taskState.value = _taskState.value.copy(importance = event.importance)
            }
            is TaskEvent.SetUrgency -> {
                _taskState.value = _taskState.value.copy(urgency = event.urgency)
            }
        }
    }

    // Function to sort tasks based on the selected SortType
    private fun sortTasks(sortType: SortType) {
        viewModelScope.launch {
            when (sortType) {
                SortType.URGENT_IMPORTANT -> {
                    dao.getUrgentImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
                SortType.NOT_URGENT_IMPORTANT -> {
                    dao.getNotUrgentImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }

                SortType.URGENT_NOT_IMPORTANT -> {
                    dao.getUrgentNotImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
                SortType.NOT_URGENT_NOT_IMPORTANT -> {
                    dao.getNotUrgentNotImportantTasks().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
                SortType.CREATED_DATE -> {
                    dao.getTasksSortedByCreatedDate().collect { taskList ->
                        _tasks.value = taskList
                    }
                }
            }
        }
    }

    // Load archived tasks sorted by created date
    fun loadArchivedTasks() {
        viewModelScope.launch {
            dao.getArchivedTasks().collect { archivedTaskList ->
                _archivedTasks.value = archivedTaskList
            }
        }
    }
}
