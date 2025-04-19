package com.example.uccmobileapp.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.misc.URLs
import com.example.uccmobileapp.databinding.FragmentAboutUccBinding

class AboutUccFragment : Fragment() {
    //create binding for About UCC Fragment
    private var _binding: FragmentAboutUccBinding? = null
    private val binding get() = _binding!!
    //create varible with youtube video data (copied from the youtube video's share option)
    //video url extracted and added to URLs object
    private val youtubeEmbed = """
        <html>
        <body style="margin:0;padding:0;">
        <iframe width="100%" height="100%" 
        src=${URLs.ABOUT_UCC_VIDEO} 
         title="YouTube video player" frameborder="0" allow="accelerometer;
          autoplay; clipboard-write; encrypted-media; gyroscope; 
        picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" 
        allowfullscreen></iframe>        
        </body>
        </html>
    """.trimIndent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAboutUccBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //assigns different settings for the WebView
        val webView: WebView = binding.uccVideoWebView
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        //loads the youtube data into the WebView element
        webView.loadData(youtubeEmbed, "text/html", "utf-8")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
