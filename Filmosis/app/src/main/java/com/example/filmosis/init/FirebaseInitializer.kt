package com.example.filmosis.init

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

/**
 * Objeto que inicializa y proporciona instancias para Firebase Authentication, Firestore y Firebase Storage.
 */
object FirebaseInitializer {
    /**
     * Instancia de FirebaseFirestore para acceder a la base de datos Firestore.
     */
    val firestoreInstance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    /**
     * Instancia de FirebaseAuth para la autenticación de usuarios.
     */
    val authInstance: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    /**
     * Instancia de FirebaseStorage para acceder al almacenamiento en la nube de Firebase.
     */
    val firebaseStorageInstance: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }
}