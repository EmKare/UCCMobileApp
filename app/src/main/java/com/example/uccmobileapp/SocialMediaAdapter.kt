package com.example.uccmobileapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SocialMediaAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val urls = listOf(
        "https://www.facebook.com/uccjamaica",
        "https://x.com/UCCjamaica?s=09",
        "https://www.instagram.com/uccjamaica?igsh=cGlka2J3OHA1Ymx5"
    )

    override fun getItemCount() = urls.size

    override fun createFragment(position: Int): Fragment {
        return WebViewFragment.newInstance(urls[position])
    }
}
