package com.example.genz_mth_app_android

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.genz_mth_app_android.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager : FragmentManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(FragmentHome())
                R.id.bottom_user -> openFragment(FragmentMyProfile())
                R.id.bottom_work -> openFragment(FragmentSetting())
                R.id.bottom_add_friend -> openFragment(FragmentAddFriend())
                R.id.bottom_friend -> openFragment(FragmentFriend())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(FragmentHome())
        binding.fab.setOnClickListener{
            Toast.makeText(this, "Tính năng", Toast.LENGTH_SHORT)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
        when(item.itemId){
            R.id.bottom_home -> openFragment(FragmentHome())
            R.id.bottom_user -> openFragment(FragmentMyProfile())
            R.id.bottom_work -> openFragment(FragmentSetting())
            R.id.bottom_add_friend -> openFragment(FragmentAddFriend())
            R.id.bottom_friend -> openFragment(FragmentFriend())

        }
    }
    override fun onBackPressed(){
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}