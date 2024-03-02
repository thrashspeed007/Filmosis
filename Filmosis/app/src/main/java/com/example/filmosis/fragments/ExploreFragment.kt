package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.CarouselMoviesAdapter
import com.example.filmosis.adapters.GenresCardViewsAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.utilities.tmdb.TmdbData

class ExploreFragment : Fragment() {
    private lateinit var rootView: View

    private val moviesAccess = MoviesAccess()

    private var trendingMovies: ArrayList<Movie> = ArrayList()
    private lateinit var trendingMoviesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_explore, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val searchView: SearchView = rootView.findViewById(R.id.explore_searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    performSearch(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        trendingMoviesRecyclerView = view.findViewById(R.id.explore_trendingMoviesRecyclerView)
        addMoviesTrendingMoviesRv()
        initGenreCardViews()
    }

    private fun performSearch(query: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MoviesSearchedFragment.newInstance(query))
            .addToBackStack(null)
            .commit()
    }

    private fun addMoviesTrendingMoviesRv() {
        moviesAccess.listTrendingMovies { result ->
            result.forEach { movie ->
                trendingMovies.add(movie)
            }

            val moviesAdapter = CarouselMoviesAdapter(trendingMovies) { movieClicked ->
                Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.vote_average)}", Toast.LENGTH_SHORT).show()
            }

            trendingMoviesRecyclerView.adapter = moviesAdapter
        }
    }

    private fun initGenreCardViews() {
        val genresRecyclerView: RecyclerView = rootView.findViewById(R.id.explore_genresRecyclerView)

        genresRecyclerView.adapter = GenresCardViewsAdapter(TmdbData.movieGenresIds)

        genresRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.HORIZONTAL, false)
    }

}