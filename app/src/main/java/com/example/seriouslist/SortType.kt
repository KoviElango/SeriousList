package com.example.seriouslist

enum class SortType {
    URGENT_IMPORTANT,         // Urgency = true, Importance = true
    NOT_URGENT_IMPORTANT,      // Urgency = false, Importance = true
    URGENT_NOT_IMPORTANT,      // Urgency = true, Importance = false
    NOT_URGENT_NOT_IMPORTANT,  // Urgency = false, Importance = false
    CREATED_DATE               // Sort by created date
}