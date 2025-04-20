package com.example.uccmobileapp.email

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast

// Helper object for email-related functionality, including validation and sending emails
object EmailHelper {

    // Sealed class to represent the validation result for email form inputs
    sealed class EmailValidationResult {
        object Valid : EmailValidationResult() // Valid input
        class Invalid(val errorMessage: String) : EmailValidationResult() // Invalid input with error message
    }

    // Helper function to check if the given email is in a valid format
    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validates the email form inputs: checks for blank fields and invalid email format
    fun validateEmailForm(yourEmail: String, subject: String, message: String): EmailValidationResult {
        return when {
            yourEmail.isBlank() -> EmailValidationResult.Invalid("Please enter your email")
            !isValidEmail(yourEmail) -> EmailValidationResult.Invalid("Invalid email format")
            subject.isBlank() -> EmailValidationResult.Invalid("Please enter a subject")
            message.isBlank() -> EmailValidationResult.Invalid("Please enter a message")
            else -> EmailValidationResult.Valid // All inputs are valid
        }
    }

    // Sends an email with the provided details
    fun sendEmail(context: Context, recipient: String, subject: String, message: String, yourEmail: String? = null) {
        // Includes the sender's email in the message if it's provided
        val fullMessage = if (yourEmail != null) {
            """
            $message

            ----------------------------
            Reply to: $yourEmail
            """.trimIndent()
        } else {
            message // If no sender email, just send the original message
        }

        // Creates an intent to send the email via an email client
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // Set MIME type for email
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient)) // Set recipient email address
            putExtra(Intent.EXTRA_SUBJECT, subject) // Set email subject
            putExtra(Intent.EXTRA_TEXT, fullMessage) // Set email body text
        }

        // Tries to start the email client
        try {
            context.startActivity(Intent.createChooser(intent, "Send Email Using..."))
        } catch (e: ActivityNotFoundException) {
            // If no email client is found, show a toast message
            Toast.makeText(context, "No email client installed", Toast.LENGTH_SHORT).show()
        }
    }
}
