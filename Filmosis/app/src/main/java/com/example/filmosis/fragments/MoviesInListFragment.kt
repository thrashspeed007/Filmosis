package com.example.filmosis.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmosis.R
import com.example.filmosis.adapters.MoviesInListAdapter
import com.example.filmosis.init.FirebaseInitializer
import com.example.filmosis.utilities.firebase.FirestoreUtilities

class MoviesInListFragment : Fragment() {

    private val firestore = FirebaseInitializer.firestoreInstance

    companion object {
        private const val ARG_LIST_ID = "listId"

        fun newInstance(listId: Int): MoviesInListFragment {
            val fragment = MoviesInListFragment()
            val args = Bundle()

            args.putInt(ARG_LIST_ID, listId)

            Log.d("MoviesFragment", listId.toString())

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_in_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val currentUserEmail = FirebaseInitializer.authInstance.currentUser?.email
        val listId = arguments?.getInt(ARG_LIST_ID)
        Log.d("MovieInListFragment",listId.toString())

        if (currentUserEmail != null && listId != null) {
            fetchDocument(currentUserEmail,listId)
        }


//        if (currentUserEmail != null && listId != null) {
//            val docRef = firestore.collection("lists").document(currentUserEmail)
//            docRef.get()
//                .addOnSuccessListener { document ->
//                    if (document.exists()) {
//                        val data = document.data
//                        if (data != null) {
//                            val listMovies = data["listMovies"] as? List<Map<String, Any>>
//
//                            if (listMovies != null) {
//                                for (movieData in listMovies) {
//                                    val movieId = (movieData["id"] as? String)?.toIntOrNull()
//                                    if (movieId == listId) {
//                                        val movieTitle = movieData["title"] as? String
//                                        val releaseDate = movieData["releaseDate"] as? String
//                                        val averageVote = movieData["averageVote"] as? Double
//
//                                        Log.d("MovieInListFragment", "Movie Data: {id: $movieId, title: $movieTitle, releaseDate: $releaseDate, averageVote: $averageVote}")
//
//                                        // Aquí puedes realizar las acciones que necesites con los datos de la película encontrada
//                                        break
//                                    }
//                                }
//                            } else {
//                                Log.d("MovieInListFragment", "No se encontró la lista de películas en el documento para el usuario $currentUserEmail.")
//                            }
//                        } else {
//                            Log.d("MovieInListFragment", "El documento está vacío para el usuario $currentUserEmail.")
//                        }
//                    } else {
//                        Log.d("MovieInListFragment", "No se encontró ningún documento para el usuario $currentUserEmail.")
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.d("MovieInListFragment", "Error al obtener el documento: $exception")
//                }
//        } else {
//            Log.d("MovieInListFragment", "El usuario no está autenticado o no se proporcionó un listId válido.")
//        }
    }

    private fun fetchDocument(username: String, desiredListId: Int) {
        val docRef = firestore.collection("lists").document(username)

        Log.d("MovieInListFragment", docRef.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                Log.d("MovieInListFragment",document.toString())
                if (document.exists()) {
                    val data = document.data
                    if (data != null) {
                        data.forEach { (key, value) ->
                            val listData = value as? Map<*, *>
                            val listId = listData?.get("listId").toString().toInt()
                            val listName = listData?.get("listName") as? String
                            val listDescription = listData?.get("listDescription") as? String
                            val listDate = listData?.get("listDate") as? String

                            Log.d("ListActivity", "List Data:")
                            Log.d("ListActivity", "  listId: $listId")
                            Log.d("ListActivity", "  listName: $listName")
                            Log.d("ListActivity", "  listDescription: $listDescription")
                            Log.d("ListActivity", "  listDate: $listDate")
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