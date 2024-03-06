package com.example.filmosis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.adapters.ListMovieAdapter
import com.example.filmosis.data.model.tmdb.ListMovie
import com.example.filmosis.init.FirebaseInitializer
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.FirebaseFirestore

class ListActivity : AppCompatActivity() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val username: String? = FirebaseInitializer.authInstance.currentUser?.email
        recyclerView = findViewById(R.id.lists_recycler1)
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
                        var index = 1 // Índice de los recyclerviews
                        val totalLists = data.size // Número total de listas en el documento

                        for ((listTitle, value) in data) {
                            if (index > 5) break // Solo manejaremos hasta 5 recyclerviews

                            val recyclerViewId = resources.getIdentifier("lists_recycler$index", "id", packageName)
                            val recyclerView = findViewById<RecyclerView>(recyclerViewId)

                            findViewById<TextView>(R.id.list_title)?.text = listTitle

                            val moviesList = mutableListOf<ListMovie>()
                            Log.d("ListActivity", "Iteración $index: $moviesList")

                            if (value is List<*>) {
                                for (entry in value) {
                                    if (entry is Map<*, *>) {
                                        val idMovie = entry["idMovie"] as? Long
                                        val title = entry["title"] as? String
                                        val director = entry["director"] as? String
                                        val date = entry["date"] as? String

                                        val movie = ListMovie(idMovie, title, director, date)
                                        moviesList.add(movie)
                                    }
                                }
                            }

                            Log.d("ListActivity", "Tamaño de la lista de películas: ${moviesList.size}")

                            val cardViewId = resources.getIdentifier("list_cardview$index", "id", packageName)
                            val cardView = findViewById<MaterialCardView>(cardViewId)

                            if (moviesList.isNotEmpty()) {
                                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                                val adapter = ListMovieAdapter(moviesList)
                                recyclerView.adapter = adapter

                                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                                        super.onScrollStateChanged(recyclerView, newState)
                                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                            adapter.centerCurrentItem(recyclerView)
                                        }
                                    }
                                })
                            } else {
                                cardView.visibility = View.GONE
                            }

                            index++
                        }
                        Log.d("ListActivity", "Número total de listas: $totalLists")

                        // Calcular el número de cardviews que se deben eliminar
                        val cardViewsToDelete = 5 - totalLists
                        if (cardViewsToDelete > 0) {
                            for (i in 1..cardViewsToDelete) {
                                val cardViewToDeleteId = resources.getIdentifier("list_cardview${5 - i + 1}", "id", packageName)
                                val cardViewToDelete = findViewById<MaterialCardView>(cardViewToDeleteId)
                                cardViewToDelete.visibility = View.GONE
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


