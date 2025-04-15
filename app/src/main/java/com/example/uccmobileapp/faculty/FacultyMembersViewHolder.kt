package com.example.uccmobileapp.faculty

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.databinding.DialogMemberDetailBinding
import com.example.uccmobileapp.databinding.FacultyItemBinding

class FacultyMembersViewHolder (private val binding: FacultyItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(facultyMember: FacultyMember) {
        binding.memberTitle.text = facultyMember.title.toString()
        binding.memberFirstName.text = facultyMember.first
        binding.memberLastName.text = facultyMember.last

        binding.root.setOnClickListener { showDetailDialog(facultyMember) }
    }

    private fun showDetailDialog(facultyMember: FacultyMember) {
        val dialogBinding = DialogMemberDetailBinding.inflate(LayoutInflater.from(binding.root.context))
            val dialog = AlertDialog.Builder(binding.root.context)
            .setView(dialogBinding.root)
            .create()

        with(dialogBinding) {
            "${facultyMember.title} ${facultyMember.first} ${facultyMember.last}".also { detailName.text = it }

            btnClose.setOnClickListener { dialog.dismiss() }
            btnCall.setOnClickListener { dialPhoneNumber(facultyMember.tele) }
            btnEmail.setOnClickListener { sendEmail(facultyMember.email) }
        }

        dialog.show()
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(binding.root.context.packageManager) != null) {
            binding.root.context.startActivity(intent)
        }
    }

    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
        }
        if (intent.resolveActivity(binding.root.context.packageManager) != null) {
            binding.root.context.startActivity(intent)
        }
    }
}