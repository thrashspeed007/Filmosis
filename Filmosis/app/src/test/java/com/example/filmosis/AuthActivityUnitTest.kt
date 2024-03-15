import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.core.app.ApplicationProvider
import com.example.filmosis.AuthActivity
import com.example.filmosis.R
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class AuthActivityUnitTest {

    private lateinit var authActivity: AuthActivity

    @Mock
    private lateinit var mockFirestore: FirebaseFirestore

    @Mock
    private lateinit var mockAuth: FirebaseAuth

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authActivity = AuthActivity()

        // Mock FirebaseFirestore and FirebaseAuth
        `when`(FirebaseInitializer.firestoreInstance).thenReturn(mockFirestore)
        `when`(FirebaseInitializer.authInstance).thenReturn(mockAuth)

        // Mock Context.getSharedPreferences() method call
        val context = ApplicationProvider.getApplicationContext<Context>()
        mockSharedPreferences = mock(SharedPreferences::class.java)
        `when`(context.getSharedPreferences(
            context.getString(R.string.prefs_file), Context.MODE_PRIVATE
        )).thenReturn(mockSharedPreferences)
    }

    @Test
    fun `test session with existing session`() {
        // Mock existing session data in SharedPreferences
        `when`(mockSharedPreferences.getString("email", null)).thenReturn("test@example.com")
        `when`(mockSharedPreferences.getString("provider", null)).thenReturn("GOOGLE")

        authActivity.session()

        // Verifica que la visibilidad del diseño de AuthActivity sea invisible cuando se detecta una sesión al iniciar la aplicación.
        val mainLayoutVisibility = authActivity.findViewById<ConstraintLayout>(R.id.mainLayout).visibility
        assertEquals("mainLayout visibility should be invisible", View.INVISIBLE, mainLayoutVisibility)
    }

    @Test
    fun `test session with no existing session`() {
        // Mock no existing session data in SharedPreferences
        `when`(mockSharedPreferences.getString("email", null)).thenReturn(null)
        `when`(mockSharedPreferences.getString("provider", null)).thenReturn(null)

        authActivity.session()

        // Verifica que la visibilidad del diseño de AuthActivity sea visible cuando no se detecta una sesión.
        val mainLayoutVisibility = authActivity.findViewById<ConstraintLayout>(R.id.mainLayout).visibility
        assertEquals("mainLayout visibility should be visible", View.VISIBLE, mainLayoutVisibility)
    }
}
