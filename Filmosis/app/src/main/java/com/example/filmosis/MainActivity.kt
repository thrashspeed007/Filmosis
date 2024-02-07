package com.example.filmosis

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result

class MainActivity : AppCompatActivity() {
    private val moviesAccess = MoviesAccess()

    private lateinit var rv: RecyclerView
    private lateinit var moviesList: ArrayList<Result>
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        rv = findViewById(R.id.moviesRecyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
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

            moviesAdapter = MoviesAdapter(moviesList)
            rv.adapter = moviesAdapter
        }
    }
}