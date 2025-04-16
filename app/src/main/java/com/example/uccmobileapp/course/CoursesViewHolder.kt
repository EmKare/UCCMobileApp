package com.example.uccmobileapp.course

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.CourseItemBinding
import com.example.uccmobileapp.databinding.DialogCourseDetailBinding

class CoursesViewHolder(private val binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {
        binding.courseCodeTextView.text = course.code
        binding.courseNameTextView.text = course.title
        "Credits: ${course.credits}".also { binding.courseCreditTextView.text = it }
        val preReq = if (course.preReqs.isNullOrEmpty()) {
            "None"
        } else {
            course.preReqs.joinToString(", ")
        }
        binding.root.setOnClickListener { showDetailDialog(course) }
    }

    private fun showDetailDialog(course: Course) {
        val dialogBinding = DialogCourseDetailBinding.inflate(LayoutInflater.from(binding.root.context))
        val dialog = AlertDialog.Builder(binding.root.context)
            .setView(dialogBinding.root)
            .create()

        with(dialogBinding) {

            detailCourseNameTextView.text = course.title
            detailCourseCodeTextView.text = course.code
            "Credits: ${course.credits}".also { detailCourseCreditTextView.text = it }
            detailCourseDescriptionTextView.text = course.description
            val preReq = if (course.preReqs.isNullOrEmpty()) {
                "None"
            } else {
                course.preReqs.joinToString(", ")
            }
            detailCoursePreRequisitesTextView.text = preReq

            detailCourseCloseButton.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}