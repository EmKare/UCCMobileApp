package com.example.uccmobileapp.misc

enum class Title(private val displayName: String) {
    MR("Mr"),
    MISS("Miss"),
    MRS("Mrs"),
    DR("Dr"),
    PROF("Prof");

    override fun toString(): String = displayName
}