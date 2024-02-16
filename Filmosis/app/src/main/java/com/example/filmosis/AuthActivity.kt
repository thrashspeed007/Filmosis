package com.example.filmosis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.fragments.ProviderType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class AuthActivity : AppCompatActivity() {

    private var loadingDialog: AlertDialog? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private val startGoogleSignInActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken)
                } catch (e: ApiException) {
                    showAlert("Error al procesar el inicio de sesión con Google.")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Setup
        session()
        setup()
    }

    override fun onStart() {
        super.onStart()
        findViewById<LinearLayout>(R.id.authLayout).visibility = View.VISIBLE
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            findViewById<LinearLayout>(R.id.authLayout).visibility = View.INVISIBLE
            showMain("")
        }
    }

    private fun setup() {
        FirebaseApp.initializeApp(this)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val nombreEditText: EditText = findViewById(R.id.nombreUsu)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val signUpButton: Button = findViewById(R.id.signUpButton)
        val logInButton: Button = findViewById(R.id.logInButton)
        val googleSignInButton: Button = findViewById(R.id.googleSignInButton)

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && nombreEditText.text.isNotEmpty()) {
                if (passwordEditText.text.toString().length > 6) {
                    auth.createUserWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {// Anadimos a la base de datos si to'do sale bien
                            saveUserInFirestore(nombreEditText.text.toString(), emailEditText.text.toString()) { success ->
                                if (success) {
                                    guardarDatos(auth.currentUser?.email ?: "", ProviderType.BASIC.toString(), auth.currentUser?.displayName ?: "")
                                    showMain(auth.currentUser.toString())
                                } else {
                                    showAlert("Error al guardar el usuario en la base de datos")
                                }
                            }
                        } else {
                            showAlert(task.exception.toString())
                        }
                    }
                } else {
                    Toast.makeText(this@AuthActivity, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                showAlert("Rellena todos los campos porfavor.")
            }
        }

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && nombreEditText.text.isNotEmpty()) {
                val email = emailEditText.text.toString()
                val userName = nombreEditText.text.toString()
                //Consultamos a nuestra base de datos si existe algun usuario con el nombre y email introducido

                val userReferencia = firestore.collection("users")//cogemos la tabla de users
                userReferencia.whereEqualTo("email", email)//comparamos si existe el email
                    .whereEqualTo("name", userName)//comparamos si existe el nombre de ususario
                    .get().addOnSuccessListener { documents ->//Si si existen que ejecute el resto de codigo
                        if(!documents.isEmpty){
                            // si ha encontrado un usuario con emai y nombre de usuario correspondiente que siga
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener { task ->
                                if (task.isSuccessful) {// Anadimos a la base de datos si to'do sale bien
                                    saveUserInFirestore(nombreEditText.text.toString(), emailEditText.text.toString()) { success ->
                                        if (success) {
                                            guardarDatos(auth.currentUser?.email ?: "", ProviderType.BASIC.toString(), auth.currentUser?.displayName ?: "")
                                            showMain(auth.currentUser.toString())
                                        } else {
                                            showAlert("Error al guardar el usuario en la base de datos")
                                        }
                                    }
                                } else {
                                    showAlert(task.exception.toString())
                                }
                            }
                        } else {
                            showAlert("No se encontró ningún usuario con el correo electrónico y el nombre de usuario proporcionados.")
                        }
                    }
                    .addOnFailureListener { exception -> //en caso que haya dado error
                        showAlert("Error al verificar las credenciales: ${exception.message}")
                    }

            } else {
                showAlert("Rellena todos los campos por favor")
            }
        }

        googleSignInButton.setOnClickListener {
//            showLoadingDialog()

            // Configuración

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)

            googleClient.signOut()

            startGoogleSignInActivityForResult.launch(googleClient.signInIntent)
        }
    }

    private fun showAlert(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario: $error")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showLoadingDialog() {
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.loading_dialog, null)

        loadingDialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()

        loadingDialog?.show()
    }

    private fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }

    // Este extra no se usa , hay que quitarlo?? en el fragmento de home se coge directamente el nombre de usuario de la db, no del extra que le pasamos aqui
    private fun showMain(userName: String) {
        //Intent creado para ir al MainActivity
        val homeIntent = Intent(this, MainActivity::class.java)
        //Pasamos nombre de usuario
        homeIntent.putExtra("userName", userName)

        startActivity(homeIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveUserInFirestore(auth.currentUser?.displayName.toString(), auth.currentUser?.email.toString()) { success ->
                        if (success) {
                            guardarDatos(auth.currentUser?.email ?: "", ProviderType.GOOGLE.toString(), auth.currentUser?.displayName ?: "")
                            showMain(auth.currentUser.toString())
                        } else {
                            showAlert("Error al guardar el usuario en la base de datos")
                        }
                    }
                } else {
                    showAlert(task.exception.toString())
                }
            }
    }

    // Guardar contraseña también cuando se cifre...
    private fun saveUserInFirestore(name: String, email: String, callback: (Boolean) -> Unit) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->// si el usuario actual no es nuulo ejecutamos el codigo que hay dentro
            val userData = hashMapOf(//creamos un map donde guardamos los datos del usuario
                "name" to name,
                "email" to email,
                //no guardamos contrasena en la base de datos ya que habria que crear metodo para cifrarla(si eso lo hacemos mas adelante) metodo creado abajo
            )

            firestore.collection("users").document(user.uid)//accedemos a la  collecion "users" y creamos un nuevo documento con el id del usauario
                .set(userData)//Establecemos la informacion del usuario
                .addOnSuccessListener {//este codigo se ejecutara si se ha guardado bien en nuestra base de datos Firesotre
                    callback(true)
                }
                .addOnFailureListener { e ->//en caso de que no haya sido exitosa saldra un mensaje de error
                    showAlert(e.message.toString())
                    callback(false)
                }
        }
    }


    private fun guardarDatos(email: String, provider: String,userName : String) {
        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.putString("userName", userName)
        prefs.apply()
    }

//    private fun hashPassword(password: String): String {
//        // Aquí debes implementar tu lógica de cifrado, por ejemplo utilizando bcrypt o SHA-256
//        // Esta es solo una implementación de ejemplo utilizando la función de hash SHA-256
//        val digest = MessageDigest.getInstance("SHA-256")
//        val encodedHash = digest.digest(password.toByteArray(StandardCharsets.UTF_8))
//        return Base64.getEncoder().encodeToString(encodedHash)
//    }

}