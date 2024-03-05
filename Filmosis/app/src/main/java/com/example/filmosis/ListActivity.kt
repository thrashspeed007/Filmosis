package com.example.filmosis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.adapters.ListMovieAdapter
import com.example.filmosis.data.model.tmdb.ListMovie
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.firestore.FirebaseFirestore

class ListActivity : AppCompatActivity() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val username: String? = FirebaseInitializer.authInstance.currentUser?.email
        recyclerView = findViewById(R.id.lists_recycler)
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
                        val moviesList = mutableListOf<ListMovie>() // Lista para almacenar las películas

                        for ((_, value) in data) {
                            if (value is List<*>) {
                                for (entry in value) {
                                    if (entry is Map<*, *>) {
                                        val movieData = entry as Map<String, Any>
                                        val idMovie = movieData["idMovie"] as? Long
                                        val title = movieData["title"] as? String
                                        val director = movieData["director"] as? String
                                        val date = movieData["date"] as? String

                                        // Crea un objeto ListMovie y agrégalo a la lista
                                        val movie = ListMovie(idMovie, title, director, date)
                                        moviesList.add(movie)
                                    }
                                }
                            }
                        }

                        Log.d("ListActivity", "Tamaño de la lista de películas: ${moviesList.size}")

                        val adapter = ListMovieAdapter(moviesList)
                        Log.d("ListActivity", adapter.itemCount.toString())
                        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
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


