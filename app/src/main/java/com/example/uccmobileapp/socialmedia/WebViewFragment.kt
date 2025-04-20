package com.example.uccmobileapp.socialmedia

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.R

// Fragment that hosts a WebView to display a given URL, complete with progress indicator,
// error handling, custom user agent tweaks, and fallback to external browser.
class WebViewFragment : Fragment() {

    // UI components
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var refreshButton: View

    companion object {
        private const val ARG_URL = "url"                 // Key for passing URL in arguments
        private const val DEFAULT_URL = "https://www.google.com" // Fallback URL if none provided

        // Factory method to create a new instance with a URL argument
        fun newInstance(url: String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, url)
                }
            }
        }
    }

    // Inflate the layout, find and assign UI components
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = view.findViewById(R.id.webView)
        progressBar = view.findViewById(R.id.progressBar)
        refreshButton = view.findViewById(R.id.btnRefresh)
        return view
    }

    // After the view is created, set up listeners and load the URL
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Reload the page when the refresh button is tapped
        refreshButton.setOnClickListener {
            webView.reload()
        }

        // Retrieve URL argument or use default
        val url = arguments?.getString(ARG_URL) ?: DEFAULT_URL

        // Load and configure the WebView
        loadUrl(url)
        configureWebView(url)
    }

    // Load the URL into the WebView; apply an Instagram-specific user agent if needed
    private fun loadUrl(url: String) {
        if (url.contains("instagram")) {
            webView.settings.userAgentString = "Instagram 219.0.0.12.117 Android"
        }
        webView.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    // Configures WebView settings for JavaScript, caching, zoom, mixed content, etc.
    private fun configureWebView(url: String) {
        webView.settings.apply {
            javaScriptEnabled = true                   // Enable JS
            domStorageEnabled = true                   // Enable DOM storage
            loadWithOverviewMode = true                // Fit content to view
            useWideViewPort = true                     // Enable responsive layout
            loadsImagesAutomatically = true
            cacheMode = WebSettings.LOAD_DEFAULT       // Default caching
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            setSupportZoom(true)                       // Enable zoom controls
            mediaPlaybackRequiresUserGesture = false   // Allow autoplay of media
            allowFileAccess = true
            allowContentAccess = true
            // Default desktop-like UA string for broader compatibility
            userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36"
        }

        // Allows third-party cookies (needed for many sites)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)

        // Handles page load callbacks and errors
        webView.webViewClient = object : WebViewClient() {
            // Hide the progress bar when loading completes
            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
            }

            // On error, notifies user and fallback to external browser
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                if (request?.isForMainFrame == true) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Could not load page. Opening in browser...", Toast.LENGTH_SHORT).show()
                    openInBrowser(url ?: DEFAULT_URL)
                }
            }
        }

        // Updates progress bar as page loads
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar.progress = newProgress
                progressBar.visibility = if (newProgress < 100) View.VISIBLE else View.GONE
            }
        }
    }

    // Launches the given URL in a Custom Tab (external browser) as a fallback
    private fun openInBrowser(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
    }

    // Cleans up the WebView to free resources and stop loading when the fragment is destroyed
    override fun onDestroyView() {
        webView.apply {
            stopLoading()
            clearHistory()
            clearCache(true)
            loadUrl("about:blank")
            destroy()
        }
        super.onDestroyView()
    }
}
