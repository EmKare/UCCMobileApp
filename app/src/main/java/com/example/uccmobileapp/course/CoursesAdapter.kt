package com.example.uccmobileapp.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.CourseItemBinding

class CoursesAdapter(private val fragment: Fragment, private val courses: List<Course>) : RecyclerView.Adapter<CoursesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(fragment.requireContext()), parent, false)
        return CoursesViewHolder(binding)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }
}