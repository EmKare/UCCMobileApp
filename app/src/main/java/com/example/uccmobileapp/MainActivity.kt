package com.example.uccmobileapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var menuButton : ImageButton
    private lateinit var navigationView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        menuButton = findViewById(R.id.menu_button)
        navigationView = findViewById(R.id.navigation_view)

        menuButton.setOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener {
            when ( it.itemId )
            {
                R.id.nav_directory ->
                {
                    Toast.makeText(this, "Dir Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_courses ->
                {
                    Toast.makeText(this, "Cor Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_admissions ->
                {
                    Toast.makeText(this, "Adm Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_social ->
                {
                    Toast.makeText(this, "Soc Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_settings ->
                {
                    Toast.makeText(this, "Set Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_contactUs ->
                {
                    Toast.makeText(this, "Con Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

    }
}