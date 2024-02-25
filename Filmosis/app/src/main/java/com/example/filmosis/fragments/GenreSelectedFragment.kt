package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.CarouselMoviesAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview

class GenreSelectedFragment : Fragment() {

    private val moviesAccess = MoviesAccess()

    private var moviesListPopulares: ArrayList<Result> = ArrayList()

    private lateinit var popularMoviesRv: CarouselRecyclerview

    companion object {
        private const val ARG_GENRE_ID = "genreId"
        private const val ARG_GENRE_NAME = "genreName"

        fun newInstance(genreId: Int, genreName: String): GenreSelectedFragment {
            val fragment = GenreSelectedFragment()
            val args = Bundle()
            args.putInt(ARG_GENRE_ID, genreId)
            args.putString(ARG_GENRE_NAME, genreName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genre_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val genreId = arguments?.getInt(ARG_GENRE_ID) ?: -1
        val genreName = arguments?.getString(ARG_GENRE_NAME) ?: ""

        view.findViewById<TextView>(R.id.genreSelected_genreTitleTextView).text = genreName

        popularMoviesRv = view.findViewById(R.id.genreSelected_popularMoviesRecyclerView)
        addPopularMoviesWithGenreToRv(listOf(genreId))
    }

    private fun addPopularMoviesWithGenreToRv(genres: List<Int>) {
        moviesAccess.listPopularMoviesWithGenres(genres) { result ->
            result.forEach { movie ->
                moviesListPopulares.add(movie)
            }

            val moviesAdapter = CarouselMoviesAdapter(moviesListPopulares) { movieClicked ->
                Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.vote_average)}", Toast.LENGTH_SHORT).show()
            }

            popularMoviesRv.adapter = moviesAdapter
        }

    }
}