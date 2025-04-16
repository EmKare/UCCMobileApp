package com.example.uccmobileapp.email

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast

object EmailHelper {

    sealed class EmailValidationResult {
        object Valid : EmailValidationResult()
        class Invalid(val errorMessage: String) : EmailValidationResult()
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateEmailForm(yourEmail: String, subject: String, message: String): EmailValidationResult {
        return when {
            yourEmail.isBlank() -> EmailValidationResult.Invalid("Please enter your email")
            !isValidEmail(yourEmail) -> EmailValidationResult.Invalid("Invalid email format")
            subject.isBlank() -> EmailValidationResult.Invalid("Please enter a subject")
            message.isBlank() -> EmailValidationResult.Invalid("Please enter a message")
            else -> EmailValidationResult.Valid
        }
    }

    fun sendEmail(context: Context, recipient: String, subject: String, message: String, yourEmail: String? = null) {
        val fullMessage = if (yourEmail != null) {
            """
            $message

            ----------------------------
            Reply to: $yourEmail
            """.trimIndent()
        } else {
            message
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, fullMessage)
        }

        try {
            context.startActivity(Intent.createChooser(intent, "Send Email Using..."))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No email client installed", Toast.LENGTH_SHORT).show()
        }
    }
}
