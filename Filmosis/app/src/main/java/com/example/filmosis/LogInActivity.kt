package com.example.filmosis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.fragments.ProviderType
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Actividad para iniciar sesión en la aplicación.
 */
class LogInActivity : AppCompatActivity() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        setup()
    }

    /**
     * Configura los elementos de la actividad y establece los listeners.
     */
    private fun setup() {
        val usernameEditText: EditText = findViewById(R.id.signUp_emailEditText)
        val passwordEditText: EditText = findViewById(R.id.signUp_passwordEditText)
        val logInBtn: Button = findViewById(R.id.logInButton)
        val returnButton: Button = findViewById(R.id.logIn_returnButton)
        val forgotPass: TextView = findViewById(R.id.logIn_forgotTextView)

        logInBtn.setOnClickListener {
            if (usernameEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                val username = usernameEditText.text.toString()
                loginUser(username, passwordEditText.text.toString())
            } else {
                showAlert("Rellena todos los campos por favor")
            }
        }

        returnButton.setOnClickListener {
            returnToAuthScreen()
        }

        forgotPass.setOnClickListener {
            val forgotIntent = Intent(this, ChangePasswordOnLogInActivity::class.java)
            startActivity(forgotIntent)
        }
    }

    /**
     * Intenta iniciar sesión con las credenciales proporcionadas.
     *
     * @param username Nombre de usuario
     * @param password Contraseña
     */
    private fun loginUser(username: String, password: String) {
        val userReference = firestore.collection("users")

        userReference.whereEqualTo("name", username)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    var userEmail = ""
                    var userFullName = ""
                    for (document in documents) {
                        userEmail = document.getString("email").toString()
                        userFullName = document.getString("fullName").toString()
                    }

                    if (!userEmail.isNullOrBlank()) {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    saveUserData(userEmail, username, userFullName)
                                    showMain()
                                } else {
                                    handleAuthException(task.exception)
                                }
                            }
                    } else {
                        showAlert("Error al obtener el email del usuario.")
                    }
                } else {
                    showAlert("Nombre de usuario o contraseña incorrectos.")
                }
            }
            .addOnFailureListener { exception ->
                showAlert("Error al verificar las credenciales: ${exception.message}")
            }
    }

    /**
     * Guarda los datos del usuario en las preferencias compartidas.
     *
     * @param email Correo electrónico del usuario
     * @param username Nombre de usuario
     * @param fullName Nombre completo del usuario
     */
    private fun saveUserData(email: String, username: String, fullName: String) {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", ProviderType.BASIC.toString())
        prefs.putString("username", username)
        prefs.putString("fullname", fullName)
        prefs.apply()
    }

    /**
     * Muestra un cuadro de diálogo de error.
     *
     * @param error Mensaje de error
     */
    private fun showAlert(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario: $error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Navega a la pantalla de inicio de sesión.
     */
    private fun returnToAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    /**
     * Maneja las excepciones relacionadas con la autenticación.
     *
     * @param exception Excepción ocurrida durante la autenticación
     */
    private fun handleAuthException(exception: Exception?) {
        if (exception is FirebaseAuthInvalidCredentialsException) {
            showAlert("Nombre de usuario o contraseña incorrectos.")
        } else {
            showAlert(exception?.message ?: "Error desconocido")
        }
    }

    /**
     * Muestra la actividad principal de la aplicación.
     */
    private fun showMain() {
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(homeIntent)
    }
}
