//package com.example.filmosis
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.example.filmosis.data.model.tmdb.Movie
//import com.example.filmosis.dataclass.ListedMovie
//import com.example.filmosis.init.FirebaseInitializer
//import com.google.firebase.firestore.FirebaseFirestore
//
//class ListActivity : AppCompatActivity() {
//
//    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list)
//
//        val username: String? = FirebaseInitializer.authInstance.currentUser?.email
//
//        if (username != null) {
//            fetchDocument(username)
//        } else {
//            Log.d("ListActivity", "No se pudo obtener el nombre de usuario.")
//        }
//    }
//
//    private fun fetchDocument(username: String) {
//        val docRef = firestore.collection("lists").document(username)
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document.exists()) {
//                    val data = document.data
//                    if (data != null) {
//                        val linearLayout = findViewById<LinearLayout>(R.id.vertical_layout)
//
//                        val listData = data["list"] as? Map<String, Any>
//
//                        if (listData != null) {
//                            val listName = listData["listName"] as? String
//                            val listDescription = listData["listDescription"] as? String
//                            val listDate = listData["listDate"] as? String
//
//                            Log.d("ListActivity", "Nombre de la lista: $listName")
//                            Log.d("ListActivity", "Descripción: $listDescription")
//                            Log.d("ListActivity", "Fecha: $listDate")
//
//                            if (listName != null && listDescription != null && listDate != null) {
//                                val nameTextView = TextView(this)
//                                nameTextView.text = "Nombre de la lista: $listName"
//                                linearLayout.addView(nameTextView)
//
//                                val descriptionTextView = TextView(this)
//                                descriptionTextView.text = "Descripción: $listDescription"
//                                linearLayout.addView(descriptionTextView)
//
//                                val dateTextView = TextView(this)
//                                dateTextView.text = "Fecha: $listDate"
//                                linearLayout.addView(dateTextView)
//                            }
//
//                            var listMovies = listData["listMovies"] as? List<Map<String, Any>>
//
//                            if (listMovies != null) {
//                                val peliculasInside : ArrayList<ListedMovie> = ArrayList()
//                                for (movie in listMovies) {
//                                    val title = movie["title"] as? String
//                                    val releaseDate = movie["releaseDate"] as? String
//                                    val averageVote = movie["averageVote"]
//                                    val averageVoteText = averageVote.toString()
//                                    val id = (movie["id"] as? Long)?.toInt()
//
//                                    // Crear mapa para cada película
//                                    val movieMap = mapOf(
//                                        "title" to title,
//                                        "releaseDate" to releaseDate,
//                                        "averageVote" to averageVote,
//                                        "id" to id
//                                    )
//
//                                    val movie = ListedMovie(
//                                        title.toString(),
//                                        releaseDate.toString(),averageVoteText, id!!)
//
//                                    peliculasInside.add(movie)
//                                }
//                                Log.d("ListActivity",peliculasInside.toString())
//                            } else {
//                                Log.d("ListActivity", "No se encontraron películas en la lista.")
//                            }
//                        } else {
//                            Log.d("ListActivity", "No se encontró la lista en los datos.")
//                        }
//                    } else {
//                        Log.d("ListActivity", "El documento está vacío para el usuario $username.")
//                    }
//                } else {
//                    Log.d("ListActivity", "No se encontró ningún documento para el usuario $username.")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("ListActivity", "Error al obtener el documento: $exception")
//            }
//    }
//
//
//}
