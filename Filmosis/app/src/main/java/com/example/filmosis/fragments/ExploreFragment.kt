package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.GenresAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.utilities.tmdb.TmdbData

class ExploreFragment : Fragment() {
    private lateinit var rootView: View

    private val moviesAccess = MoviesAccess()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_explore, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        initGenreCardViews()
    }

    private fun initGenreCardViews() {
        val genresRecyclerView: RecyclerView = rootView.findViewById(R.id.explore_genresRecyclerView)

        genresRecyclerView.adapter = GenresAdapter(TmdbData.movieGenresIds)

        genresRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.HORIZONTAL, false)
    }

}