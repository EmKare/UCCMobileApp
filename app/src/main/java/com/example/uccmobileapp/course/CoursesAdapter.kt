package com.example.uccmobileapp.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R

class CoursesAdapter(private val fragment: Fragment, private val courses: List<Course>) : RecyclerView.Adapter<CoursesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val inflater = LayoutInflater.from(fragment.requireContext())
        val view = inflater.inflate(R.layout.course_item, parent, false)
        return CoursesViewHolder(view)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }
}