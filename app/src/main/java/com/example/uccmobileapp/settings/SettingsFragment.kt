package com.example.uccmobileapp.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uccmobileapp.R
import com.example.uccmobileapp.databinding.FragmentSettingsBinding

// Fragment for the settings screen, allowing users to toggle dark mode, open website, view map, and learn about the app
class SettingsFragment : Fragment() {

    // ViewBinding object for interacting with the layout's views
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    // Inflate the fragment's layout and initialize binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called after the view has been created; set up listeners and default values for UI components
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets the initial state of the dark mode switch based on current night mode
        binding.darkModeSwitch.isChecked =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        // Sets listener for the dark mode switch to toggle between light and dark mode
        //binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
        //    AppCompatDelegate.setDefaultNightMode(
        //        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES // Enables dark mode
        //        else AppCompatDelegate.MODE_NIGHT_NO           // Enables light mode
        //    )
        //}

        // Opens the UCC website when the button is clicked
        binding.openWebsiteBtn.setOnClickListener {
            val url = "https://ucc.edu.jm" // UCC homepage URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)  // Launches the browser with the URL
        }

        // Opens UCC's location in Google Maps when the "View Map" button is clicked
        binding.viewMapBtn.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=University of the Commonwealth Caribbean, Kingston")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps") // Prefer Google Maps
            }

            // If Google Maps is available, use it; otherwise, open the map with the default app
            if (mapIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                startActivity(Intent(Intent.ACTION_VIEW, uri)) // Opens with any available map app
            }
        }

        // Shows an "About" dialog with app details when the "About" button is clicked
        binding.aboutBtn.setOnClickListener {
            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("About This App")
                .setMessage("UCC Mobile App\nVersion 1.0\nDeveloped by ${binding.root.context.resources.getString(
                    R.string.app_dev_team)}")  // Displays the app's developer team info
                .setPositiveButton("OK", null)  // Closes the dialog when the "OK" button is clicked
                .create()
            dialog.show()  // Show the dialog
        }
    }

    // Cleans up binding when the fragment view is destroyed to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
