package com.example.uccmobileapp.home


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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var handler : Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            R.drawable.winston_headshot,
            R.drawable.karen_headshot,
            R.drawable.damien_headshot,
            R.drawable.alicia_headshot,
            R.drawable.nia_headshot,
            R.drawable.michael_headshot,
            R.drawable.rajiv_headshot,
            R.drawable.theodore_headshot,
        )

        val galleryAdapter = ImageGalleryAdapter(imageList)
        binding.imageSlider.adapter = galleryAdapter
        handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val nextItem = (binding.imageSlider.currentItem + 1) % imageList.size
                binding.imageSlider.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 4000) // every 4 seconds
            }
        }
        handler.postDelayed(runnable, 4000)

        binding.imageSlider.offscreenPageLimit = 1

        binding.homeUCCMappreview.setOnClickListener {
            val mapUri = Uri.parse("geo:0,0?q=University of the Commonwealth Caribbean, Kingston")
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri).apply {
                setPackage("com.google.android.apps.maps")
            }

            if (mapIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                //Toast.makeText(requireContext(), "Google Maps not available, using default app.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(Intent.ACTION_VIEW, mapUri))
            }
        }

        binding.homeUCCVisitWebsiteButton.setOnClickListener {
            val url = URLs.UCC_HOMEPAGE
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }
}
