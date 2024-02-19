package com.example.filmosis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.fragments.ProviderType
import com.example.filmosis.init.FirebaseInitializer
import com.example.filmosis.utilities.FirestoreUtilities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance
    private val auth: FirebaseAuth = FirebaseInitializer.authInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setup()
    }

    private fun setup() {
        val emailEditText: EditText = findViewById(R.id.signUp_emailEditText)
        val passwordEditText: EditText = findViewById(R.id.signUp_passwordEditText)
        val usernameEditText: EditText = findViewById(R.id.signUp_usernameEditText)
        val signUpButton: Button = findViewById(R.id.signUp_signUpButton)
        val returnButton: Button = findViewById(R.id.signUp_returnButton)

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && usernameEditText.text.isNotEmpty()) {
                if (passwordEditText.text.toString().length > 6) {
                    val username = usernameEditText.text.toString()

                    val users = firestore.collection("users")

                    users.whereEqualTo("name", username)
                        .get().addOnSuccessListener { documents ->
                            if(!documents.isEmpty){
                                showAlert("El nombre de usuario introducido ya está en uso por otra cuenta")
                            } else {
                                auth.createUserWithEmailAndPassword(
                                    emailEditText.text.toString(),
                                    passwordEditText.text.toString()
                                ).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        FirestoreUtilities.saveUserInFirestore(firestore, auth, usernameEditText.text.toString(), emailEditText.text.toString()) { success ->
                                            if (success) {
                                                guardarDatos(emailEditText.text.toString(), ProviderType.BASIC.toString(), usernameEditText.text.toString())
                                                showMain()
                                            } else {
                                                showAlert("Error al guardar el usuario en la base de datos")
                                            }
                                        }
                                    } else {
                                        if (task.exception is FirebaseAuthUserCollisionException) {
                                            showAlert("El email introducido ya está en uso por otra cuenta")
                                        } else {
                                            showAlert(task.exception.toString())
                                        }
                                    }
                                }
                            }
                        }
                } else {
                    Toast.makeText(this@SignUpActivity, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                showAlert("Rellena todos los campos porfavor.")
            }
        }

        returnButton.setOnClickListener {
            returnToAuthScreen()
        }
    }

    private fun showMain() {
        //Intent creado para ir al MainActivity
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(homeIntent)
    }

    private fun returnToAuthScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun guardarDatos(email: String, provider: String, username : String) {
        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.putString("username", username)
        prefs.apply()
    }

    private fun showAlert(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al crear la cuenta: $error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}