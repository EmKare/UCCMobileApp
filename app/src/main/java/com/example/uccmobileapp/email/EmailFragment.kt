package com.example.uccmobileapp.email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.uccmobileapp.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {

    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "To: ithod@ucc.edu.jm".also { binding.hodFragmentHODEmail.text = it }

        binding.hodFragmentSendEmailButton.setOnClickListener {

            val yourEmail = binding.hodFragmentYourEmail.text.toString().trim()
            val subject = binding.hodFragmentSubject.text.toString().trim()
            val message = binding.hodFragmentMessage.text.toString().trim()

            val validation = EmailHelper.validateEmailForm(yourEmail, subject, message)
            if (validation is EmailHelper.EmailValidationResult.Valid) {
                EmailHelper.sendEmail(binding.root.context, "ithod@ucc.edu.jm", subject, message, yourEmail)
            } else if (validation is EmailHelper.EmailValidationResult.Invalid) {
                Toast.makeText(binding.root.context, validation.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}