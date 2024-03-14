package com.example.filmosis

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.core.app.ApplicationProvider
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AuthActivityUnitTest {

    private lateinit var authActivity: AuthActivity

    @Mock
    private lateinit var mockFirestore: FirebaseFirestore

    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authActivity = AuthActivity()

        mockFirestore = FirebaseInitializer.firestoreInstance
        mockAuth = FirebaseInitializer.authInstance
        
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file), Context.MODE_PRIVATE
        )
        `when`(context.getSharedPreferences(
            context.getString(R.string.prefs_file), Context.MODE_PRIVATE
        )).thenReturn(prefs)
    }

    @Test
    fun `test session with existing session`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file), Context.MODE_PRIVATE
        )

        val email = "test@example.com"
        val provider = "GOOGLE"
        prefs.edit().putString("email", email).putString("provider", provider).apply()

        authActivity.session()

        // Verifica que la visibilidad del layout del authActivity es invisible cuando la es detectada una sesión al iniciar la app
        val mainLayoutVisibility = authActivity.findViewById<ConstraintLayout>(R.id.mainLayout).visibility
        assertEquals("mainLayout visibility should be invisible", View.INVISIBLE, mainLayoutVisibility)
    }

    @Test
    fun `test session with no existing session`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file), Context.MODE_PRIVATE
        )

        // Clear any existing session
        prefs.edit().clear().apply()

        // Call session method
        authActivity.session()

        // Verifica que la visibilidad del layout del authActivity es visible cuando no se detecta una sesión
        val mainLayoutVisibility = authActivity.findViewById<ConstraintLayout>(R.id.mainLayout).visibility
        assertEquals("mainLayout visibility should be visible", View.VISIBLE, mainLayoutVisibility)
    }

    // Add more unit tests for other methods as needed
}
