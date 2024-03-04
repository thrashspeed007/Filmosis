package com.example.filmosis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.firestore.FirebaseFirestore

class ListActivity : AppCompatActivity() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val username: String? = FirebaseInitializer.authInstance.currentUser?.email
        if (username != null) {
            fetchDocument(username)
        } else {
            Log.d("ListActivity", "No se pudo obtener el nombre de usuario.")
        }
    }

    private fun fetchDocument(username: String) {
        val docRef = firestore.collection("lists").document(username)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val data = document.data
                    if (data != null) {
                        for ((key, value) in data) {
                            if (value is List<*>) {
                                for (entry in value) {
                                    if (entry is Map<*, *>) {
                                        val movie = entry as Map<String, Any>
                                        val idMovie = movie["idMovie"] as? Long
                                        val title = movie["title"] as? String
                                        val director = movie["director"] as? String
                                        val date = movie["date"] as? String
                                        Log.d("ListActivity", "idMovie: $idMovie, title: $title, director: $director, date: $date")
                                    }
                                }
                            }
                        }
                    } else {
                        Log.d("ListActivity", "El documento está vacío para el usuario $username.")
                    }
                } else {
                    Log.d("ListActivity", "No se encontró ningún documento para el usuario $username.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ListActivity", "Error al obtener el documento: $exception")
            }

    }
}


