package com.example.uccmobileapp.course

//code, name, credits, pre-requisites, description

// Data class representing a university course with all necessary details
data class Course(
    val code: String,              // Unique course code (e.g., "IT101")
    val title: String,             // Full name of the course (e.g., "Introduction to Programming")
    val credits: Int,              // Number of credit hours awarded for completing the course
    val preReqs: List<String>,     // List of prerequisite course codes required before enrollment
    val description: String         // Detailed description of the course content
)
