package com.example.filmosis

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.fragments.ExploreFragment
import com.example.filmosis.fragments.HomeFragment
import com.example.filmosis.fragments.SocialFragment
import com.example.filmosis.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.checkerframework.common.subtyping.qual.Bottom
import java.lang.Math.round
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentHome : HomeFragment
    private lateinit var fragmentExplore : ExploreFragment
    private lateinit var fragmentSocial : SocialFragment
    private lateinit var fragmentUser : UserFragment

    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        fragmentHome = HomeFragment()
        fragmentExplore = ExploreFragment()
        fragmentSocial = SocialFragment()
        fragmentUser = UserFragment()

        replaceFragment(fragmentHome)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_home -> replaceFragment(fragmentHome)
                R.id.page_explore -> replaceFragment(fragmentExplore)
                R.id.page_social -> replaceFragment(fragmentSocial)
                R.id.page_user -> replaceFragment(fragmentUser)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, fragment)

        transaction.addToBackStack(null)

        transaction.commit()
    }
}