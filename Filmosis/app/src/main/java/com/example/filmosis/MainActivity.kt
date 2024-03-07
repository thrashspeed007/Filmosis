package com.example.filmosis

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.filmosis.fragments.ExploreFragment
import com.example.filmosis.fragments.HomeFragment
import com.example.filmosis.fragments.ListsFragment
import com.example.filmosis.fragments.SocialFragment
import com.example.filmosis.fragments.UserFragment
import com.example.filmosis.init.FirebaseInitializer
import com.example.filmosis.utilities.firebase.FirestoreImageManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        initBottomNavigationViewAndFragments()
        initDrawerLayout()
        checkProfilePic()
    }

    private fun checkProfilePic() {
        if (FirestoreImageManager.isTemporaryImageUriEmpty()) {
            val storageReference = FirebaseInitializer.firebaseStorageInstance.reference

            val username : String? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)?.getString("username","")
            if (!username.isNullOrBlank()) {
                val profilePicSrc: String = "profilepic_$username.jpg"

                val imagesRef = storageReference.child("images")

                imagesRef.listAll()
                    .addOnSuccessListener { listResult ->
                        for (item in listResult.items) {
                            if (item.name == profilePicSrc) {
                                item.downloadUrl.addOnSuccessListener { uri ->
                                    val imageUrl = uri.toString()
                                    FirestoreImageManager.setTemporaryImageUri(imageUrl)
                                    val drawerNavigationView: NavigationView = findViewById(R.id.main_drawerNavigationView)
                                    Glide.with(applicationContext).load(imageUrl).into(drawerNavigationView.getHeaderView(0).findViewById(R.id.drawerHeader_profilePic))
                                }.addOnFailureListener { exception ->
                                    Log.d("Profile Pic", "Error al obtener la URL de la imagen", exception)
                                }
                                return@addOnSuccessListener
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("Profile Pic", "Error al verificar la existencia del archivo en Firebase Storage", exception)
                    }
            }
        }
    }

    private fun initBottomNavigationViewAndFragments() {
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragmentHome = HomeFragment()
        val fragmentExplore = ExploreFragment()
        val fragmentSocial = SocialFragment()
        val fragmentUser = UserFragment()

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

    private fun initDrawerLayout() {
        val drawerLayout : DrawerLayout = findViewById(R.id.main_drawerLayout)
        val drawerLayoutToggleBtn: ImageButton = findViewById(R.id.main_drawerLayoutToggle)

        drawerLayoutToggleBtn.setOnClickListener {
            drawerLayout.open()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        val drawerNavigationView: NavigationView = findViewById(R.id.main_drawerNavigationView)

        val drawerHeader = drawerNavigationView.getHeaderView(0)

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        drawerHeader.findViewById<TextView>(R.id.drawerHeader_usernameTextView).text = prefs.getString("username", "")
        drawerHeader.findViewById<TextView>(R.id.drawerHeader_userEmailTextView).text = prefs.getString("email", "")

        drawerNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                // TODO
                R.id.drawerMenu_myLists -> {
                    replaceFragment(ListsFragment())
                    val drawerLayout : DrawerLayout = findViewById(R.id.main_drawerLayout)
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, fragment)

        transaction.commit()
    }

}