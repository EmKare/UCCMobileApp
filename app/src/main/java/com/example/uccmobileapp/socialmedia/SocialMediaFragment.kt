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
class SocialMediaFragment : Fragment() {

    private val tabTitles = listOf("Instagram", "Tiktok", "Twitter", "Facebook",)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_social_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = SocialMediaAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.black_instagram_icon)
                1 -> tab.setIcon(R.drawable.black_tiktok_icon)
                2 -> tab.setIcon(R.drawable.black_x_icon)
                3 -> tab.setIcon(R.drawable.black_facebook_icon)
            }
            tab.contentDescription = tabTitles[position]
        }.attach()
    }
}
