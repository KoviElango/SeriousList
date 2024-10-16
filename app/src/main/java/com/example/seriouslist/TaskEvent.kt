package com.example.seriouslist

import com.example.seriouslist.local_data_storage.TaskEntity
import java.util.Date

sealed interface TaskEvent {
    // Create Task events
    object SaveTask: TaskEvent
    data class SetTaskName(val itemName: String): TaskEvent
    data class SetDeadline(val deadline: Date): TaskEvent
    data class SetImportance(val importance: Boolean): TaskEvent
    data class SetUrgency(val urgency: Boolean): TaskEvent

    // Events for querying tasks by quadrants or sorted list
    object GetUrgentImportantTasks: TaskEvent
    object GetNotUrgentImportantTasks: TaskEvent
    object GetUrgentNotImportantTasks: TaskEvent
    object GetNotUrgentNotImportantTasks: TaskEvent
    object GetTasksSortedByCreatedDate: TaskEvent

    // New event for sorting tasks by SortType
    data class SortTasks(val sortType: SortType): TaskEvent

    // Events for task actions after creation
    data class DeleteTask(val task: TaskEntity): TaskEvent
    data class ArchiveTask(val task: TaskEntity): TaskEvent
    data class UpdateTask(val task: TaskEntity): TaskEvent
}
