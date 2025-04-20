package com.example.uccmobileapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.uccmobileapp.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen") // Suppresses lint warning for using a custom splash screen
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding // ViewBinding for the splash screen layout

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflates the splash screen layout using ViewBinding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animates the logo and title on splash screen with fade-in and scale effects
        binding.splashLogo.animate()
            .alpha(1f)   // Fades in the logo
            .scaleX(1f)  // Scales the logo to its original size horizontally
            .scaleY(1f)  // Scales the logo to its original size vertically
            .setDuration(800)  // Sets animation duration to 800ms
            .start()  // Starts the animation

        binding.splashTitle.animate()
            .alpha(1f)   // Fades in the title
            .scaleX(1f)  // Scales the title to its original size horizontally
            .scaleY(1f)  // Scales the title to its original size vertically
            .setDuration(800)  // Sets animation duration to 800ms
            .start()  // Starts the animation

        // Uses a Handler to introduce a delay before transitioning to the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Starts MainActivity after the delay
            startActivity(Intent(this, MainActivity::class.java))
            // Applies fade-in and fade-out transitions during activity switch
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            // Finishes SplashActivity so it is removed from the back stack
            finish()
        }, 2500) // Waits for 2.5 seconds before transitioning
    }
}
