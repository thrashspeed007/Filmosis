package com.example.filmosis.utilities.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtilities {

    fun saveUserInFirestore(firestore: FirebaseFirestore, auth: FirebaseAuth, name: String, email: String, callback: (Boolean) -> Unit) {
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
                .addOnFailureListener {
                    callback(false)
                }
        }
    }
}