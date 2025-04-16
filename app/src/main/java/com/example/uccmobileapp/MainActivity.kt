package com.example.uccmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.uccmobileapp.admissions.AdmissionsFragment
import com.example.uccmobileapp.course.CoursesDatabaseHelper
import com.example.uccmobileapp.course.CoursesFragment
import com.example.uccmobileapp.course.ITCourses
import com.example.uccmobileapp.databinding.ActivityMainBinding
import com.example.uccmobileapp.faculty.FacultyFragment
import com.example.uccmobileapp.faculty.FacultyMembers
import com.example.uccmobileapp.faculty.FacultyMembersDatabaseHelper
import com.example.uccmobileapp.socialmedia.SocialMediaFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() ,FabVisibilityListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var menuButton : ImageButton
    private lateinit var navigationView : NavigationView
    private lateinit var emailFAB : FloatingActionButton

    private lateinit var coursesDBHelper : CoursesDatabaseHelper
    private lateinit var facultyDBHelper : FacultyMembersDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coursesDBHelper = CoursesDatabaseHelper(binding.root.context)

        if (coursesDBHelper.isCoursesTableEmpty()) {
            for (course in ITCourses) {
                coursesDBHelper.insertCourse(course)
            }
        }

        val courses = coursesDBHelper.getAllCourses()

        facultyDBHelper = FacultyMembersDatabaseHelper(binding.root.context)

        if (facultyDBHelper.isFacultyTableEmpty()) {
            for (facultyMember in FacultyMembers) {
                facultyDBHelper.insertFacultyMembers(facultyMember)
            }
        }

        val facultyMembers = facultyDBHelper.getAllFacultyMembers()

        drawerLayout = binding.drawerLayout
        menuButton = binding.menuButton
        navigationView = binding.navigationView
        emailFAB = binding.emailFab

        menuButton.setOnClickListener {
            drawerLayout.open()
        }

        emailFAB.setOnClickListener{
            drawerLayout.close()
            sendEmailToHOD()
            //goToFragment(EmailFragment())
            //EmailHelper.sendEmail(this, "hod@ucc.edu", "Inquiry from App", "Hello, I’d like to ask about...",)
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
                    hideFab()
                    goToFragment(FacultyFragment(facultyMembers))
                    drawerLayout.close()
                    true
                }
                R.id.nav_courses ->
                {
                    showFab()
                    goToFragment(CoursesFragment(courses))
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
                else -> false
            }
        }
        if (savedInstanceState == null) {
            goToFragment(HomeFragment())
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    private fun goToFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    private fun sendEmailToHOD() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:ithod@ucc.edu.jm")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Department Inquiry")
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