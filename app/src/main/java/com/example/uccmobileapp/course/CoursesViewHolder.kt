package com.example.uccmobileapp.course

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R
import com.example.uccmobileapp.databinding.CourseItem1Binding
import com.example.uccmobileapp.databinding.DialogCourseDetail2Binding

// ViewHolder for individual course items in the RecyclerView

class CoursesViewHolder(private val binding: CourseItem1Binding) : RecyclerView.ViewHolder(binding.root) {

    // Binds a Course object’s data to the item’s views
    fun bind(course: Course) {
        // Sets course code and name
        binding.courseCodeTextView.text = course.code
        binding.courseNameTextView.text = course.title
        // Displays credits with label
        "Credits: ${course.credits}".also { binding.courseCreditTextView.text = it }

        // Determines drawable resource IDs based on course code
        var imageID = binding.root.context.resources.getIdentifier(
            "${course.code.lowercase()}_icon",
            "drawable",
            binding.root.context.packageName
        )
        var imageID2 = binding.root.context.resources.getIdentifier(
            "${course.code.lowercase()}_icon2",
            "drawable",
            binding.root.context.packageName
        )

        // Fallback to generic icons if specific ones aren’t found
        if (imageID == 0) {
            imageID = binding.root.context.resources.getIdentifier(
                "general_course_icon",
                "drawable",
                binding.root.context.packageName
            )
        }
        if (imageID2 == 0) {
            imageID2 = binding.root.context.resources.getIdentifier(
                "general_course_icon2",
                "drawable",
                binding.root.context.packageName
            )
        }

        // Sets the primary icon on the item view
        binding.courseImage.setImageResource(imageID)

        // When the item is clicked, show a detailed dialog
        binding.root.setOnClickListener {
            showDetailDialog(course, imageID, imageID2)
        }
    }

    // Displays a dialog with detailed course information
    private fun showDetailDialog(course: Course, imageID: Int, imageID2: Int) {
        // Inflates the custom dialog layout
        val dialogBinding = DialogCourseDetail2Binding.inflate(
            LayoutInflater.from(binding.root.context)
        )
        // Builds and configure the AlertDialog
        val dialog = AlertDialog.Builder(binding.root.context)
            .setView(dialogBinding.root)
            .create()

        with(dialogBinding) {
            // Populates dialog views with course details
            detailCourseNameTextView.text = course.title
            detailCourseCodeTextView.text = course.code
            "Credits: ${course.credits}".also { detailCourseCreditTextView.text = it }
            detailCourseDescriptionTextView.text = course.description

            // Formats prerequisites list or show "None" if empty
            val preReq = if (course.preReqs.isNullOrEmpty()) {
                "None"
            } else {
                course.preReqs.joinToString(", ")
            }
            detailCoursePreRequisitesTextView.text = preReq

            // Sets both primary and secondary course images
            detailCourseImage.setImageResource(imageID)
            detailNextCourseImage.setImageResource(imageID2)

            // Dismisses dialog when close button is clicked
            detailCourseCloseButton.setOnClickListener {
                dialog.dismiss()
            }
        }

        // Finally, shows the dialog on screen
        dialog.show()
    }
}
