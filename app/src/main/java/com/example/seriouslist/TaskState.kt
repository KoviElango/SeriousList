package com.example.seriouslist

import java.util.Date

data class TaskState(
    val taskName: String = "",
    val deadline: Date = Date(),
    val importance: Boolean = false,
    val urgency: Boolean = false
)
