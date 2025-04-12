package com.example.uccmobileapp

enum class Title(private val displayName: String) {
    MR("Mr."),
    MS("Ms."),
    MRS("Mrs.");

    override fun toString(): String = displayName
}