package com.example.uccmobileapp.email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uccmobileapp.databinding.FragmentEmailBinding

// Fragment for handling email sending functionality
class EmailFragment : Fragment() {

    // ViewBinding object for interacting with the layout's views
    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    // Inflates the fragment's layout and initializes the binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Initializes the UI after the view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Displays the recipient email address for the HOD
        "To: ithod@ucc.edu.jm".also { binding.hodFragmentHODEmail.text = it }

        // Sets up listener for the "Send Email" button
        binding.hodFragmentSendEmailButton.setOnClickListener {

            // Gets user input values for email, subject, and message
            val yourEmail = binding.hodFragmentYourEmail.text.toString().trim()
            val subject = binding.hodFragmentSubject.text.toString().trim()
            val message = binding.hodFragmentMessage.text.toString().trim()

            // Validates the email form input using the EmailHelper utility
            val validation = EmailHelper.validateEmailForm(yourEmail, subject, message)
            if (validation is EmailHelper.EmailValidationResult.Valid) {
                // If valid, send the email via EmailHelper
                EmailHelper.sendEmail(binding.root.context, "ithod@ucc.edu.jm", subject, message, yourEmail)
            } else if (validation is EmailHelper.EmailValidationResult.Invalid) {
                // If invalid, show an error message as a toast
                Toast.makeText(binding.root.context, validation.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Cleans up binding reference to prevent memory leaks when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
