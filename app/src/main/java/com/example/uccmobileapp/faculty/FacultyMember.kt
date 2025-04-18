package com.example.uccmobileapp.faculty

import com.example.uccmobileapp.misc.Title

//name, photo, telephone number, and email of each member.

data class FacultyMember(
    val title: Title,
    val first: String,
    val last: String,
    val email: String,
    val tele: String,
    val role: String,
    val about: String,
    //val photo: String
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