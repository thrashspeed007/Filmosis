package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListedMoviesAdapter
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result

class MoviesSearchedFragment : Fragment() {
    private val moviesAccess = MoviesAccess()
    private lateinit var rv: RecyclerView

    private var moviesList: ArrayList<Result> = ArrayList()

    companion object {
        private const val ARG_QUERY = "tmdbQuery"

        fun newInstance(query: String): MoviesSearchedFragment {
            val fragment = MoviesSearchedFragment()
            val args = Bundle()

            args.putString(ARG_QUERY, query)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_searched, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val query = arguments?.getString(ARG_QUERY)

        rv = view.findViewById(R.id.moviesSearched_recyclerView)
        if (query != null) {
            addSearchedMoviesToRv(query)
        }
    }

    private fun addSearchedMoviesToRv(query: String) {
        moviesAccess.searchMovie(query) { result ->
            result.forEach { movie ->
                moviesList.add(movie)
            }
        }

        val moviesAdapter = ListedMoviesAdapter(moviesList) { movieClicked ->
            // TODO
            // LLEVAR A PANTALLA DE ACTIVIDAD DE PELICULAS
            Toast.makeText(requireContext(), "xd", Toast.LENGTH_SHORT).show()
        }

        rv.adapter = moviesAdapter
    }


}