package com.example.filmosis.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.MoviesInListAdapter
import com.example.filmosis.dataclass.ListedMovie
import com.example.filmosis.init.FirebaseInitializer

class MoviesInListFragment : Fragment() {

    private val firestore = FirebaseInitializer.firestoreInstance

    companion object {
        private const val ARG_LIST_ID = "listId"

        fun newInstance(listId: String): MoviesInListFragment {
            val fragment = MoviesInListFragment()
            val args = Bundle()

            args.putString(ARG_LIST_ID, listId)

            Log.d("MoviesFragment", listId)

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
        val listId = arguments?.getString(ARG_LIST_ID)
        Log.d("MovieInListFragment",listId.toString())

        if (currentUserEmail != null && listId != null) {
            fetchDocument(currentUserEmail,listId)
        }


    }

    private fun fetchDocument(username: String, desiredListId: String) {
        val docRef = firestore.collection("lists").document(username)

        docRef.get()
            .addOnSuccessListener { document ->
                Log.d("MovieInListFragment", document.toString())
                if (document.exists()) {
                    val data = document.data
                    if (data != null) {
                        data.forEach { (key, value) ->
                            val listData = value as? Map<*, *>
                            val listId = listData?.get("listId").toString()
                            val listName = listData?.get("listName") as? String
                            val listDescription = listData?.get("listDescription") as? String
                            val listDate = listData?.get("listDate") as? String

                            if (listId == desiredListId) {
                                val listMovies = listData?.get("listMovies") as? List<Map<String, Any>>?
                                val listedMovies = listMovies?.map { movie ->
                                    val averageVote = movie["averageVote"].toString().toDouble()
                                    val movieId = movie["id"].toString().toInt()
                                    val releaseDate = movie["releaseDate"] as? String ?: ""
                                    val title = movie["title"] as? String ?: ""

                                    ListedMovie(title, releaseDate, averageVote, movieId)
                                }?.toMutableList()

                                listedMovies?.forEachIndexed { index, listedMovie ->
                                    Log.d("ListActivity", "  Movie $index:")
                                    Log.d("ListActivity", "    movieId: ${listedMovie.id}")
                                    Log.d("ListActivity", "    title: ${listedMovie.title}")
                                    Log.d("ListActivity", "    releaseDate: ${listedMovie.releaseDate}")
                                    Log.d("ListActivity", "    averageVote: ${listedMovie.averageVote}")
                                }

                                // Inicialización de información de lista y recycler view

                                if (listName != null && listDescription != null) {
                                    initListInfo(listName, listDescription)
                                }

                                if (listedMovies != null) {
                                    initRv(listedMovies)
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

    private fun initRv(listedMovies: MutableList<ListedMovie>){

        val rv = requireView().findViewById<RecyclerView>(R.id.moviesInList_moviesRecyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        rv.adapter = MoviesInListAdapter(
            listedMovies,

            onMovieClick = {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(it.id))
            transaction.addToBackStack(null)
            transaction.commit()
            },

            isDeleteable = true,

            onDeleteMovie = {
                deleteMovieFromFirestore(it.id)
            })
    }

    private fun initListInfo(listName: String, listDescription: String) {
        requireView().findViewById<TextView>(R.id.moviesInList_listTitle).text = listName
        requireView().findViewById<TextView>(R.id.moviesInList_listDescription).text = listDescription
    }

    private fun deleteMovieFromFirestore(movieId: Int) {
        val userEmail = FirebaseInitializer.authInstance.currentUser?.email.toString()
        val listsRef = firestore.collection("lists").document(userEmail)
        val desiredListId = arguments?.getInt(ARG_LIST_ID)


        // TODO
        // NO PUEDO TIO

        listsRef
            .get()
            .addOnSuccessListener { document ->
                val data = document.data

                data?.forEach { (key, value) ->
                    val listData = value as? Map<*, *>
                    val listId = listData?.get("listId").toString().toInt()

                    if (listId == desiredListId) {
                        val moviesList = document.get("listMovies") as? MutableList<Map<String, Any>>
                        val movieRemoved = moviesList?.removeIf { it["movieId"] == movieId }

                        if (movieRemoved == true) {
                            listsRef.update("listMovies", moviesList)
                                .addOnSuccessListener {
                                    Log.d("bruh", "Movie deleted successfully.")
                                }
                                .addOnFailureListener { exception ->
                                    Log.d("bruh", "Error updating document: $exception")
                                }
                        }

                    } else {
                        Toast.makeText(requireContext(), "bruh", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.d("bruh", "Error fetching lists: $exception")
            }
    }

}