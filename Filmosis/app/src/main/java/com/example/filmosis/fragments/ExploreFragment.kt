package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.filmosis.MovieClickListener
import com.example.filmosis.MoviesAdapter
import com.example.filmosis.R
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result

class ExploreFragment : Fragment() {
    private val moviesAccess = MoviesAccess()

    private lateinit var rv: RecyclerView
    private lateinit var moviesList: ArrayList<Result>
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        rv = view.findViewById(R.id.moviesRecyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv)
        moviesList = ArrayList()

        addMoviesToList()
    }

    private fun addMoviesToList() {
        moviesAccess.listPopularMovies { result ->
            result.forEach { movie ->
                moviesList.add(movie)
            }

            moviesAdapter = MoviesAdapter(moviesList,
                object : MovieClickListener {
                    override fun onMovieClick(movie: Result) {
                        Toast.makeText(requireContext(), "Puntuación media: ${(movie.vote_average)}", Toast.LENGTH_SHORT).show()
                    }
                })
            rv.adapter = moviesAdapter
        }
    }

}