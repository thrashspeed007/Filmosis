package com.example.filmosis.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


    }

    private fun fetchDocument(username: String, desiredListId: Int) {
        val docRef = firestore.collection("lists").document(username)

        Log.d("MovieInListFragment", docRef.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                Log.d("MovieInListFragment", document.toString())
                if (document.exists()) {
                    val data = document.data
                    if (data != null) {
                        data.forEach { (key, value) ->
                            val listData = value as? Map<*, *>
                            val listId = listData?.get("listId").toString().toInt()
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
                                }

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

    private fun initRv(listedMovies: List<ListedMovie>){

        val rv = requireView().findViewById<RecyclerView>(R.id.moviesInList_moviesRecyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        rv.adapter = MoviesInListAdapter(listedMovies) {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(it.id))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initListInfo(listName: String, listDescription: String) {
        requireView().findViewById<TextView>(R.id.moviesInList_listTitle).text = listName
        requireView().findViewById<TextView>(R.id.moviesInList_listDescription).text = listDescription
    }
}