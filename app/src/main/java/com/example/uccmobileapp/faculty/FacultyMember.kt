package com.example.uccmobileapp.faculty

import com.example.uccmobileapp.misc.Title

//name, photo, telephone number, and email of each member.

// Data class to represent a faculty member with essential details like name, title, contact info, role, and about
data class FacultyMember(
    val title: Title,    // The faculty member's title (Mr., Dr., etc.)
    val first: String,   // First name of the faculty member
    val last: String,    // Last name of the faculty member
    val email: String,   // Email address of the faculty member
    val tele: String,    // Telephone number of the faculty member
    val role: String,    // Role/position of the faculty member in the department
    val about: String,   // Short bio or about section for the faculty member
    //val photo: String    // Optionally, a path to the photo of the faculty member (currently commented out)
)

/*
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FacultyMember

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}

*/