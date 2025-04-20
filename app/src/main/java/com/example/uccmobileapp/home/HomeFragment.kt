package com.example.uccmobileapp.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.R
import com.example.uccmobileapp.misc.URLs
import com.example.uccmobileapp.databinding.FragmentHomeBinding

// Fragment representing the home screen with an image slider, map preview, and website link
class HomeFragment : Fragment() {

    // ViewBinding object for interacting with the layout's views
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Handler for scheduling automatic image slider scrolls
    private lateinit var handler: Handler

    // Inflates the fragment's layout and initialize binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called immediately after onCreateView; set up UI components here
    @SuppressLint("QueryPermissionsNeeded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // List of drawable resource IDs for the image slider
        val imageList = listOf(
            R.drawable.ucc_location,
            R.drawable.register_now,
            R.drawable.graduation,
            R.drawable.ucc_slb_banner,
            R.drawable.scholarship,
            R.drawable.invigilator_needed
        )

        // Initializes the gallery adapter with the image list
        val galleryAdapter = ImageGalleryAdapter(imageList)
        binding.imageSlider.adapter = galleryAdapter

        // Sets up handler and runnable for auto-scrolling the image slider
        handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                // Determines the next index, wrapping around at the end
                val nextItem = (binding.imageSlider.currentItem + 1) % imageList.size
                binding.imageSlider.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 4000) // Scroll every 4 seconds
            }
        }
        handler.postDelayed(runnable, 4000) // Start auto-scroll after initial delay

        // Keeps one page loaded on either side for smooth swiping
        binding.imageSlider.offscreenPageLimit = 1

        // Sets up click listener on map preview to open location in Maps
        binding.homeUCCMapPreview.setOnClickListener {
            // Creates a geo URI query for the university address
            val mapUri = Uri.parse("geo:0,0?q=University of the Commonwealth Caribbean, Kingston")
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri).apply {
                setPackage("com.google.android.apps.maps") // Prefer Google Maps app
            }

            // If Google Maps isn't available, open with any available map app
            if (mapIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                startActivity(Intent(Intent.ACTION_VIEW, mapUri))
            }
        }

        // Sets up click listener on "Visit Website" button to open the UCC homepage
        binding.homeUCCVisitWebsiteButton.setOnClickListener {
            val url = URLs.UCC_HOMEPAGE
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    // Cleans up handler callbacks and binding when view is destroyed to prevent memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }
}
