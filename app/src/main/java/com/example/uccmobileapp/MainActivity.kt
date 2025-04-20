package com.example.uccmobileapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.about.AboutUccFragment
import com.example.uccmobileapp.admissions.AdmissionsFragment
import com.example.uccmobileapp.course.CoursesDatabaseHelper
import com.example.uccmobileapp.course.CoursesFragment
import com.example.uccmobileapp.course.ITCourses
import com.example.uccmobileapp.databinding.ActivityMainBinding
import com.example.uccmobileapp.faculty.FacultyFragment
import com.example.uccmobileapp.faculty.FacultyMembers
import com.example.uccmobileapp.faculty.FacultyMembersDatabaseHelper
import com.example.uccmobileapp.home.HomeFragment
import com.example.uccmobileapp.settings.SettingsFragment
import com.example.uccmobileapp.socialmedia.SocialMediaFragment

//MainActivity hosts the navigation drawer, handles fragment transactions,
//initializes local databases for courses and faculty, and controls the email FAB.
class MainActivity : AppCompatActivity(), FabVisibilityListener {

    // ViewBinding for accessing views in activity_main.xml
    private lateinit var binding: ActivityMainBinding

    // Helpers for accessing SQLite databases for courses and faculty
    private lateinit var coursesDBHelper: CoursesDatabaseHelper
    private lateinit var facultyDBHelper: FacultyMembersDatabaseHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout via ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display app version in the footer TextView
        val versionText = findViewById<TextView>(R.id.appVersion)
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        versionText.text = "Version $versionName"

        // Initialize and populate the courses database if empty
        coursesDBHelper = CoursesDatabaseHelper(this)
        if (coursesDBHelper.isCoursesTableEmpty()) {
            for (course in ITCourses) {
                coursesDBHelper.insertCourse(course)
            }
        }
        val courses = coursesDBHelper.getAllCourses()

        // Initialize and populate the faculty database if empty
        facultyDBHelper = FacultyMembersDatabaseHelper(this)
        if (facultyDBHelper.isFacultyTableEmpty()) {
            for (member in FacultyMembers) {
                facultyDBHelper.insertFacultyMembers(member)
            }
        }
        val facultyMembers = facultyDBHelper.getAllFacultyMembers()

        // Open navigation drawer when menu button is clicked
        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        // Send email to HOD when FAB is clicked, and close drawer
        binding.emailFab.setOnClickListener {
            binding.drawerLayout.close()
            sendEmailToHOD()
        }

        // Handle navigation drawer menu item selections
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    showFab()                                  // Show FAB on home screen
                    goToFragment(HomeFragment())               // Load HomeFragment
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_directory -> {
                    hideFab()                                  // Hide FAB on directory
                    goToFragment(FacultyFragment(facultyMembers))
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_courses -> {
                    showFab()                                  // Show FAB on courses list
                    goToFragment(CoursesFragment(courses))
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_admissions -> {
                    showFab()                                  // Show FAB on admissions page
                    goToFragment(AdmissionsFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_social -> {
                    hideFab()                                  // Hide FAB on social media viewer
                    goToFragment(SocialMediaFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_settings -> {
                    showFab()                                  // Show FAB on settings page
                    goToFragment(SettingsFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_about_ucc -> {
                    showFab()                                  // Show FAB on about page
                    goToFragment(AboutUccFragment())
                    binding.drawerLayout.close()
                    true
                }
                else -> false
            }
        }

        // On first launch, load HomeFragment and mark it selected
        if (savedInstanceState == null) {
            goToFragment(HomeFragment())
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    //Replace the fragment in the container and add transaction to back stack.
    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    //Launches an email intent to contact the HOD.
    private fun sendEmailToHOD() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:ithod@ucc.edu.jm")
            // Note: EXTRA_SUBJECT should be set on this Intent object, not on 'intent' property
            putExtra(Intent.EXTRA_SUBJECT, "Department Inquiry")
        }
        startActivity(Intent.createChooser(intent, "Send Email"))
    }

    //Show the email FAB (implements FabVisibilityListener).
    override fun showFab() {
        binding.emailFab.show()
    }

    //Hide the email FAB (implements FabVisibilityListener).
    override fun hideFab() {
        binding.emailFab.hide()
    }
}
