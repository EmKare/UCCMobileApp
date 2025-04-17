package com.example.uccmobileapp.socialmedia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.uccmobileapp.URLs

class SocialMediaAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val urls = listOf(
        URLs.UCC_INSTAGRAM,
        URLs.UCC_TIKTOK,
        URLs.UCC_TWITTER,
        URLs.UCC_FACEBOOK,
    )

    override fun getItemCount() = urls.size

    override fun createFragment(position: Int): Fragment {
        return WebViewFragment.newInstance(urls[position])
    }

}
