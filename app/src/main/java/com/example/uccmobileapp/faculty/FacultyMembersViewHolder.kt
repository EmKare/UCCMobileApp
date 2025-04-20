package com.example.uccmobileapp.faculty

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R
import com.example.uccmobileapp.databinding.FacultyItem2Binding

// ViewHolder for individual faculty items in the RecyclerView
class FacultyMembersViewHolder(
    private val binding: FacultyItem2Binding  // Binding object for the faculty item layout
) : RecyclerView.ViewHolder(binding.root) {

    // Binds a FacultyMember object’s data to the item’s views
    fun bind(facultyMember: FacultyMember) {
        // Displays the full name with title (e.g., "Dr. Jane Doe")
        "${facultyMember.title}. ${facultyMember.first} ${facultyMember.last}"
            .also { binding.facultyMemberFullName.text = it }

        // Displays the faculty member's role/position
        binding.facultyMemberRoleTextView.text = facultyMember.role

        // Displays the about/biography text
        binding.facultyMemberAbout.text = facultyMember.about

        // Sets up click listener for the call button to dial the phone number
        binding.facultyCallButton.setOnClickListener {
            dialPhoneNumber(facultyMember.tele)
        }

        // Set up click listener for the email button to compose an email
        binding.facultyEmailButton.setOnClickListener {
            sendEmail(facultyMember.email)
        }

        //binding.facultyImageView.setImageResource(R.drawable.winston_headshot)

        // Try to load a headshot drawable based on the member's first name
        val imageID = binding.root.context.resources.getIdentifier(
            "${facultyMember.first.lowercase()}_headshot",
            "drawable",
            binding.root.context.packageName
        )

        // If a specific headshot is found, use it; otherwise, use a default icon
        if (imageID != 0) {
            binding.facultyImageView.setImageResource(imageID)
        } else {
            binding.facultyImageView.setImageResource(R.drawable.round_person_24)
        }
    }

    // Helper method to launch the phone dialer with the given number
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        // Use a chooser in case multiple dialer apps are installed
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Dialing"))
    }

    // Helper method to launch an email client for the given address
    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
        }
        // Use a chooser to let the user select their preferred email app
        val context = binding.root.context
        context.startActivity(Intent.createChooser(intent, "Send Email"))
    }
}