package com.example.filmosis.utilities.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

/**
 * Clase singleton que proporciona utilidades para interactuar con Firestore en Firebase.
 */
object FirestoreUtilities {

    /**
     * Guarda la información del usuario en Firestore.
     *
     * @param firestore Instancia de [FirebaseFirestore].
     * @param auth Instancia de [FirebaseAuth].
     * @param username Nombre de usuario.
     * @param email Correo electrónico del usuario.
     * @param fullName Nombre completo del usuario.
     * @param birthDate Fecha de nacimiento del usuario en formato de cadena.
     * @param callback Función de retorno de llamada que indica si la operación fue exitosa o no.
     */
    fun saveUserInFirestore(firestore: FirebaseFirestore, auth: FirebaseAuth, username: String, email: String, fullName: String, birthDate: String, callback: (Boolean) -> Unit) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->// si el usuario actual no es nuulo ejecutamos el codigo que hay dentro
            val userData = hashMapOf(//creamos un map donde guardamos los datos del usuario
                "username" to username,
                "email" to email,
                "fullName" to fullName,
                "birthDate" to birthDate
                //no guardamos contrasena en la base de datos ya que habria que crear metodo para cifrarla(si eso lo hacemos mas adelante) metodo creado abajo
            )

            firestore.collection("users").document(user.uid)//accedemos a la  collecion "users" y creamos un nuevo documento con el id del usauario
                .set(userData)//Establecemos la informacion del usuario
                .addOnSuccessListener {//este codigo se ejecutara si se ha guardado bien en nuestra base de datos Firesotre
                    callback(true)
                }
                .addOnFailureListener {
                    callback(false)
                }
        }
    }

    /**
     * Crea una entrada de lista de usuario en Firestore.
     *
     * @param firestore Instancia de [FirebaseFirestore].
     * @param email Correo electrónico del usuario.
     */
    fun createUserListEntryInFirestore(firestore: FirebaseFirestore, email: String) {
        firestore.collection("lists").document(email)
            .set(hashMapOf<String, Any>())
    }
}