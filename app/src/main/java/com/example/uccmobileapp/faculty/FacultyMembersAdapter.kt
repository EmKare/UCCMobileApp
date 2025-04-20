package com.example.uccmobileapp.faculty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.FacultyItem2Binding

// Adapter for displaying a list of faculty members in a RecyclerView
class FacultyMembersAdapter(private val fragment: Fragment, private val facultyMembers: List<FacultyMember>) : RecyclerView.Adapter<FacultyMembersViewHolder>() {

    // Creates a new ViewHolder instance when a new item is needed in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyMembersViewHolder {
        // Inflates the layout for individual faculty items using ViewBinding
        val binding = FacultyItem2Binding.inflate(LayoutInflater.from(fragment.requireContext()), parent, false)
        return FacultyMembersViewHolder(binding) // Return a new ViewHolder with the binding
    }

    // Binds the data (FacultyMember) to the ViewHolder at the given position
    override fun onBindViewHolder(holder: FacultyMembersViewHolder, position: Int) {
        holder.bind(facultyMembers[position]) // Pass the faculty member data to the ViewHolder's bind function
    }

    // Returns the total number of faculty members in the list
    override fun getItemCount(): Int = facultyMembers.size
}
