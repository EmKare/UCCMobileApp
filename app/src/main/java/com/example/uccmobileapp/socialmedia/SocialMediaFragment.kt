package com.example.uccmobileapp.socialmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.uccmobileapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// Fragment to display social media pages in a tabbed layout with ViewPager2
class SocialMediaFragment : Fragment() {

    // List of tab titles corresponding to the social media platforms
    private val tabTitles = listOf("Instagram", "Tiktok", "Twitter", "Facebook")

    // Inflate the fragment layout when the view is created
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_social_media, container, false)
    }

    // Set up the TabLayout and ViewPager2 after the view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout) // Find TabLayout by ID
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager) // Find ViewPager2 by ID

        // Initialize the adapter to handle fragments for each social media platform
        val adapter = SocialMediaAdapter(this)
        viewPager.adapter = adapter // Set the adapter for ViewPager2

        // Connect the TabLayout and ViewPager2 with a TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set an icon for each tab based on its position
            when (position) {
                0 -> tab.setIcon(R.drawable.black_instagram_icon) // Instagram icon
                1 -> tab.setIcon(R.drawable.black_tiktok_icon)   // TikTok icon
                2 -> tab.setIcon(R.drawable.black_x_icon)        // Twitter icon
                3 -> tab.setIcon(R.drawable.black_facebook_icon) // Facebook icon
            }
            // Set content description for accessibility purposes
            tab.contentDescription = tabTitles[position]
        }.attach() // Attach the mediator to synchronize TabLayout with ViewPager2
    }
}
