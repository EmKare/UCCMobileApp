package com.example.uccmobileapp.faculty

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R
import com.example.uccmobileapp.databinding.FacultyItem2Binding

class FacultyMembersViewHolder (private val binding: FacultyItem2Binding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(facultyMember: FacultyMember) {
        "${facultyMember.title}. ${facultyMember.first} ${facultyMember.last}".also { binding.facultyMemberFullName.text = it }
        binding.facultyMemberRoleTextView.text = facultyMember.role
        binding.facultyMemberAbout.text = facultyMember.about
        binding.facultyCallButton.setOnClickListener { dialPhoneNumber(facultyMember.tele) }
        binding.facultyEmailButton.setOnClickListener { sendEmail(facultyMember.email) }
        //binding.facultyImageView.setImageResource(R.drawable.winston_headshot)
        val imageID = binding.root.context.resources.getIdentifier("${facultyMember.first.lowercase()}_headshot", "drawable", binding.root.context.packageName)
        if (imageID != 0) {
            binding.facultyImageView.setImageResource(imageID)
        } else {
            binding.facultyImageView.setImageResource(R.drawable.round_person_24)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Dailling"))
    }

    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
        }
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Send Email"))
    }
}