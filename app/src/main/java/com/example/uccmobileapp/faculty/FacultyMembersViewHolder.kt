package com.example.uccmobileapp.faculty

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.FacultyItemBinding

class FacultyMembersViewHolder (private val binding: FacultyItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(facultyMember: FacultyMember) {
        "${facultyMember.title}. ${facultyMember.first} ${facultyMember.last}".also { binding.facultyMemberFullName.text = it }
        binding.facultyMemberRoleTextView.text = facultyMember.role
        binding.facultyMemberAbout.text = facultyMember.about
        binding.facultyCallButton.setOnClickListener { dialPhoneNumber(facultyMember.tele) }
        binding.facultyEmailButton.setOnClickListener { sendEmail(facultyMember.email) }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Calling"))
    }

    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
        }
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Send Email"))
    }
}