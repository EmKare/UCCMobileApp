package com.example.uccmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.uccmobileapp.admissions.AdmissionsFragment
import com.example.uccmobileapp.course.CoursesFragment
import com.example.uccmobileapp.databinding.ActivityMainBinding
import com.example.uccmobileapp.email.EmailFragment
import com.example.uccmobileapp.faculty.FacultyFragment
import com.example.uccmobileapp.socialmedia.SocialMediaFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() ,FabVisibilityListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var menuButton : ImageButton
    private lateinit var navigationView : NavigationView
    private lateinit var fragmentManager : FragmentManager
    private lateinit var emailFAB : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        menuButton = binding.menuButton
        navigationView = binding.navigationView
        emailFAB = binding.emailFab

        menuButton.setOnClickListener {
            drawerLayout.open()
        }

        emailFAB.setOnClickListener{
            drawerLayout.close()
            goToFragment(EmailFragment())
            //sendEmailToHOD()
        }

        navigationView.setNavigationItemSelectedListener {
            when ( it.itemId )
            {
                R.id.nav_home ->
                {
                    showFab()
                    goToFragment(HomeFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_directory ->
                {
                    showFab()
                    goToFragment(FacultyFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_courses ->
                {
                    showFab()
                    goToFragment(CoursesFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_admissions ->
                {
                    showFab()
                    goToFragment(AdmissionsFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_social ->
                {
                    hideFab()
                    goToFragment(SocialMediaFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_settings ->
                {
                    showFab()
                    goToFragment(SettingsFragment())
                    drawerLayout.close()
                    true
                }
                R.id.nav_contactUs ->
                {
                    showFab()
                    Toast.makeText(this, "Con Clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.close()
                    true
                }
                else -> false
            }
        }
    }

    private fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    private fun sendEmailToHOD() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:hod@ucc.edu")
            putExtra(Intent.EXTRA_SUBJECT, "Department Inquiry")
        }
        startActivity(Intent.createChooser(intent, "Send Email"))

    }

    override fun showFab() {
        binding.emailFab.show()
    }

    override fun hideFab() {
        binding.emailFab.hide()
    }
}