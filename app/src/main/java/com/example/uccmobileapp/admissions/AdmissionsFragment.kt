package com.example.uccmobileapp.admissions

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.URLs
import com.example.uccmobileapp.databinding.FragmentAdmissionsBinding


class AdmissionsFragment : Fragment() {

    private var _binding: FragmentAdmissionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.admissionsFragRequirementsForInternationalButton.setOnClickListener {
            openWebPage(URLs.REQUIREMENTS_INTERNATIONAL)
        }

        binding.admissionsFragVisitWebsiteButton.setOnClickListener {
            openWebPage(URLs.VISIT_WEBSITE)
        }

        binding.admissionsFragApplyOnlineButton.setOnClickListener {
            openWebPage(URLs.APPLY_ONLINE)
        }
    }

    private fun openWebPage(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "No browser app installed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}