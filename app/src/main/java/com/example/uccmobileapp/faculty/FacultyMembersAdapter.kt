package com.example.uccmobileapp.faculty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.FacultyItemBinding

class FacultyMembersAdapter(private val fragment: Fragment, private val facultyMembers: List<FacultyMember>) : RecyclerView.Adapter<FacultyMembersViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyMembersViewHolder {
        val binding = FacultyItemBinding.inflate(LayoutInflater.from(fragment.requireContext()), parent, false)
        return FacultyMembersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyMembersViewHolder, position: Int) {
        holder.bind(facultyMembers[position])
    }

    override fun getItemCount(): Int = facultyMembers.size
}