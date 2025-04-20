package com.example.uccmobileapp.admissions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.misc.URLs
import com.example.uccmobileapp.databinding.FragmentAdmissionsBinding

class AdmissionsFragment : Fragment() {

    // ViewBinding instance for interacting with views in the layout
    private var _binding: FragmentAdmissionsBinding? = null
    private val binding get() = _binding!!

    // Inflates the fragment's layout and initializes binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdmissionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Sets up click listeners after the view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Opens webpage for international student requirements
        binding.admissionsFragRequirementsForInternationalButton.setOnClickListener {
            openWebPage(URLs.REQUIREMENTS_INTERNATIONAL)
        }

        // Opens the general admissions page
        binding.admissionsFragVisitWebsiteButton.setOnClickListener {
            openWebPage(URLs.VISIT_ADMISSIONS)
        }

        // Opens the application page for applying online
        binding.admissionsFragApplyOnlineButton.setOnClickListener {
            openWebPage(URLs.APPLY_ONLINE)
        }
    }

    // Clears binding reference to prevent memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Helper method to launch a web page using an Intent
    private fun openWebPage(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            // Handles case where no browser is installed
            Toast.makeText(
                requireContext(),
                "No browser app installed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
