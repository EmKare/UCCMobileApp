package com.example.uccmobileapp.course

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R

class CoursesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val codeTextView: TextView = itemView.findViewById(R.id.courseCodeTextView)
    private val nameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
    private val creditsTextView: TextView = itemView.findViewById(R.id.courseCreditTextView)
    private val preRequisites: TextView = itemView.findViewById(R.id.coursePreRequisitesTextView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.courseDescriptionTextView)

    fun bind(course: Course) {

            codeTextView.text = course.code
            nameTextView.text = course.title
            creditsTextView.text = "Credits: ${course.credits}"
            val preReq = if (course.preReqs.isNullOrEmpty()) {
                "None"
            } else {
                course.preReqs.joinToString(", ")
            }
            preRequisites.text = "Prerequisites: $preReq"
            descriptionTextView.text = course.description
    }
}