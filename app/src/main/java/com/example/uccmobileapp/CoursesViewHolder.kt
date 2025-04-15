package com.example.uccmobileapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val codeTextView: TextView = itemView.findViewById(R.id.courseCodeTextView)
    private val nameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
    private val creditsTextView: TextView = itemView.findViewById(R.id.courseCreditTextView)
    private val preRequisites: TextView = itemView.findViewById(R.id.coursePreRequisitesTextView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.courseDescriptionTextView)

    fun bind(course: Course) {

            codeTextView.text = course.code ?: "N/A"
            nameTextView.text = course.title ?: "Untitled Course"
            creditsTextView.text = "Credits: ${course.credits}"
            val preReq = if (course.preReqs.isNullOrEmpty()) {
                "None"
            } else {
                course.preReqs.joinToString(", ")
            }
            preRequisites.text = "Prerequisites: $preReq"
            descriptionTextView.text = course.description ?: "No description"
    }
}