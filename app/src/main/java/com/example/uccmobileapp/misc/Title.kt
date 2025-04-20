package com.example.uccmobileapp.misc

// Enumeration of titles used for FacultyMember objects
enum class Title(private val displayName: String) {
    MR("Mr"),    // Mister
    MISS("Miss"),// Miss
    MRS("Mrs"),  // Misses
    DR("Dr"),    // Doctor
    PROF("Prof");// Professor

    // Override toString to return the human-readable display name
    override fun toString(): String = displayName
}
