package com.example.uccmobileapp.course

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R
import com.example.uccmobileapp.databinding.CourseItem1Binding
import com.example.uccmobileapp.databinding.DialogCourseDetail2Binding

class CoursesViewHolder(private val binding: CourseItem1Binding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {
        binding.courseCodeTextView.text = course.code
        binding.courseNameTextView.text = course.title
        "Credits: ${course.credits}".also { binding.courseCreditTextView.text = it }

        var imageID = binding.root.context.resources.getIdentifier("${course.code.lowercase()}_icon", "drawable", binding.root.context.packageName)
        var imageID2 = binding.root.context.resources.getIdentifier("${course.code.lowercase()}_icon2", "drawable", binding.root.context.packageName)
        if (imageID == 0) {
            imageID = binding.root.context.resources.getIdentifier("general_course_icon", "drawable", binding.root.context.packageName)
        }
        if (imageID2 == 0) {
            imageID2 = binding.root.context.resources.getIdentifier("general_course_icon2", "drawable", binding.root.context.packageName)
        }
        binding.courseImage.setImageResource(imageID)

        binding.root.setOnClickListener { showDetailDialog(course, imageID, imageID2) }
    }

    private fun showDetailDialog(course: Course, imageID: Int, imageID2: Int) {
        val dialogBinding = DialogCourseDetail2Binding.inflate(LayoutInflater.from(binding.root.context))
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
            detailCourseImage.setImageResource(imageID)
            detailNextCourseImage.setImageResource(imageID2)
            detailCourseCloseButton.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}