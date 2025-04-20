package com.example.uccmobileapp.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.CourseItem1Binding

// RecyclerView Adapter for displaying a list of Course objects
class CoursesAdapter(
    private val fragment: Fragment,           // The fragment where the adapter is used
    private val courses: List<Course>         // The list of courses to be displayed
) : RecyclerView.Adapter<CoursesViewHolder>() {

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        // Inflates the item layout using ViewBinding
        val binding = CourseItem1Binding.inflate(LayoutInflater.from(fragment.requireContext()), parent, false)
        return CoursesViewHolder(binding)
    }

    // Returns the total number of items in the data set
    override fun getItemCount(): Int = courses.size

    // Binds data to the ViewHolder at the given position
    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }
}
