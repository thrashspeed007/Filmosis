package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.filmosis.MoviesAdapter
import com.example.filmosis.R
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.utilities.tmdb.tmdbData

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

    // TODO
    // Hacer que los cardviews se generen automaticamente, no ponerlos manualmente en el layout...
    // También poner fotos de cada género...
    private fun initGenreCardViews() {
        val linearLayout: LinearLayout = rootView.findViewById(R.id.explore_genresHorizontalLinearLayout)
        var genreMapIndex = 0

        for (i in 0 until linearLayout.childCount) {
            val childView = linearLayout.getChildAt(i)

            if (childView is LinearLayout) {
                for (i in 0 until childView.childCount) {
                    val cardView = childView.getChildAt(i)
                    val genreId = tmdbData.movieGenresIds[genreMapIndex].first
                    val genreName = tmdbData.movieGenresIds[genreMapIndex].second

                    cardView.findViewById<TextView>(R.id.textViewGenre).text = genreName

                    // Asignarle el on click al cardview para que abra actividad de genero... con peticion a api y toda la jodienda...
                    cardView.setOnClickListener {
                        // TODO
                        Toast.makeText(requireContext(), "Id: $genreId, Genero: $genreName", Toast.LENGTH_SHORT).show()
                    }

                    genreMapIndex++
                }
            }
        }
    }

}