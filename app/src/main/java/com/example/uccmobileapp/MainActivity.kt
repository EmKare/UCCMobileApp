package com.example.uccmobileapp

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

class MainActivity : AppCompatActivity() ,FabVisibilityListener {

    private lateinit var binding : ActivityMainBinding

    private lateinit var coursesDBHelper : CoursesDatabaseHelper
    private lateinit var facultyDBHelper : FacultyMembersDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val versionText = findViewById<TextView>(R.id.appVersion)
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        versionText.text = "Version $versionName"

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

        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.emailFab.setOnClickListener{
            binding.drawerLayout.close()
            sendEmailToHOD()
            //goToFragment(EmailFragment())
            //EmailHelper.sendEmail(this, "hod@ucc.edu", "Inquiry from App", "Hello, I’d like to ask about...",)
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when ( it.itemId )
            {
                R.id.nav_home ->
                {
                    showFab()
                    goToFragment(HomeFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_directory ->
                {
                    hideFab()
                    binding.drawerLayout.close()
                    goToFragment(FacultyFragment(facultyMembers))
                    true
                }
                R.id.nav_courses ->
                {
                    showFab()
                    goToFragment(CoursesFragment(courses))
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_admissions ->
                {
                    showFab()
                    goToFragment(AdmissionsFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_social ->
                {
                    hideFab()
                    goToFragment(SocialMediaFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_settings ->
                {
                    showFab()
                    goToFragment(SettingsFragment())
                    binding.drawerLayout.close()
                    true
                }
                R.id.nav_about_ucc ->
                {
                    showFab()
                    goToFragment(AboutUccFragment())
                    binding.drawerLayout.close()
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            goToFragment(HomeFragment())
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }

        //layoutInflater.inflate(R.layout.nav_footer, binding.navigationView, true)
        //binding.navigationView.addView(footerView)




    }

    private fun goToFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
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