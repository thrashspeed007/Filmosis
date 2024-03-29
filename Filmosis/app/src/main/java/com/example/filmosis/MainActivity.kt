package com.example.filmosis

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.filmosis.fragments.ExploreFragment
import com.example.filmosis.fragments.HomeFragment
import com.example.filmosis.fragments.ListsFragment
import com.example.filmosis.fragments.UserFragment
import com.example.filmosis.init.FirebaseInitializer
import com.example.filmosis.utilities.firebase.FirestoreImageManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

/**
 * Actividad principal de la aplicación.
 *
 * Esta actividad se encarga de gestionar la navegación entre fragmentos, el menú lateral, y la configuración del tema.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Método llamado al crear la actividad.
     * Se configura la interfaz de usuario, se inicializan los fragmentos y se configuran los listeners.
     *
     * @param savedInstanceState contiene datos previamente guardado del estado de la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    /**
     * Configura la actividad.
     */
    private fun setup() {
        initBottomNavigationViewAndFragments()
        initDrawerLayout()
        checkProfilePic()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
    }

    /**
     * Infla el menú de opciones en la barra de herramientas.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    /**
     * Gestiona la selección de elementos en el menú de opciones.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbarMenu_themeMenu -> {
                val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                    // Cambiar al modo claro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    // Cambiar al modo oscuro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate() // Reiniciar la actividad para aplicar el cambio de tema
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Verifica si el usuario tiene una foto de perfil y la carga en el menú lateral.
     */
    private fun checkProfilePic() {
        val drawerNavigationView: NavigationView = findViewById(R.id.main_drawerNavigationView)

        if (FirestoreImageManager.isTemporaryImageUriEmpty()) {
            val storageReference = FirebaseInitializer.firebaseStorageInstance.reference

            val email : String? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)?.getString("email","")
            if (!email.isNullOrBlank()) {
                val profilePicSrc: String = "profilepic_$email.jpg"

                val imagesRef = storageReference.child("images")

                imagesRef.listAll()
                    .addOnSuccessListener { listResult ->
                        for (item in listResult.items) {
                            if (item.name == profilePicSrc) {
                                item.downloadUrl.addOnSuccessListener { uri ->
                                    val imageUrl = uri.toString()
                                    FirestoreImageManager.setTemporaryImageUri(imageUrl)
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
        } else {
            Glide.with(applicationContext).load(FirestoreImageManager.getTemporaryImageUri()).into(drawerNavigationView.getHeaderView(0).findViewById(R.id.drawerHeader_profilePic))
        }
    }

    /**
     * Inicializa la barra de navegación inferior y los fragmentos asociados.
     */
    private fun initBottomNavigationViewAndFragments() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragmentHome = HomeFragment()
        val fragmentExplore = ExploreFragment()
        val fragmentUser = UserFragment()

        replaceFragment(fragmentHome)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            val newFragment = when (menuItem.itemId) {
                R.id.page_home -> fragmentHome
                R.id.page_explore -> fragmentExplore
                R.id.page_user -> fragmentUser
                else -> fragmentHome
            }

            if (currentFragment != null && currentFragment != newFragment) {
                replaceFragment(newFragment)
            }

            true
        }
    }


    /**
     * Inicializa el diseño del menú lateral.
     */
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
                R.id.drawerMenu_myLists -> {
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    if (currentFragment?.tag != "LISTS_FRAGMENT") {
                        replaceFragment(ListsFragment(),"LISTS_FRAGMENT")
                    }
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    /**
     * Reemplaza el fragmento actual por uno nuevo.
     *
     * @param fragment El nuevo fragmento a mostrar.
     * @param tag La etiqueta opcional para identificar el fragmento.
     */
    private fun replaceFragment(fragment: Fragment, tag : String = "") {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        if (tag.isNotEmpty()) {
            transaction.replace(R.id.fragmentContainerView, fragment,tag)
            transaction.addToBackStack(null)
        } else {
            transaction.replace(R.id.fragmentContainerView, fragment)
        }


        transaction.commit()
    }

}