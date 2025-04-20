package com.example.uccmobileapp.socialmedia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.uccmobileapp.misc.URLs

// Adapter for ViewPager2 to display different social media pages in WebViewFragments
class SocialMediaAdapter(
    fragment: Fragment // The parent fragment hosting the ViewPager2
) : FragmentStateAdapter(fragment) {

    // List of social media URLs to display (order determines the ViewPager pages)
    private val urls = listOf(
        URLs.UCC_INSTAGRAM, // Instagram page URL
        URLs.UCC_TIKTOK,    // TikTok page URL
        URLs.UCC_TWITTER,   // Twitter page URL
        URLs.UCC_FACEBOOK   // Facebook page URL
    )

    // Returns the total number of pages (one per URL)
    override fun getItemCount(): Int = urls.size

    // Creates a new WebViewFragment for the given position, passing in the corresponding URL
    override fun createFragment(position: Int): Fragment {
        // WebViewFragment.newInstance(url) initializes a fragment displaying the URL in a WebView
        return WebViewFragment.newInstance(urls[position])
    }
}
