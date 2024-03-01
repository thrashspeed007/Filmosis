package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.CarouselMoviesAdapter
import com.example.filmosis.adapters.ListedMoviesAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result

class GenreSelectedFragment : Fragment() {

    private val moviesAccess = MoviesAccess()

    private var moviesListTrending: ArrayList<Result> = ArrayList()
    private var filteredMoviesList: ArrayList<Result> = ArrayList()

    private lateinit var genres: List<Int>

    private lateinit var popularMoviesRv: RecyclerView
    private lateinit var moviesFilteredRv: RecyclerView

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

        genres = listOf(genreId)

        view.findViewById<TextView>(R.id.genreSelected_genreTitleTextView).text = genreName

        popularMoviesRv = view.findViewById(R.id.genreSelected_popularMoviesRecyclerView)
        // No hace falta layoutManager porque es Carousel recycler view y ya viene integrado
        addTrendingMoviesWithGenreToRv(genres)

        moviesFilteredRv = view.findViewById(R.id.genreSelected_moviesFilteredRecyclerView)
        moviesFilteredRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        initFilterMoviesButtons(view)
    }

    private fun initFilterMoviesButtons(view: View) {
        val bestRatedBtn: Button = view.findViewById(R.id.genreSelected_topRatedBtn)
        val popularBtn: Button = view.findViewById(R.id.genreSelected_popularBtn)
        val latestBtn: Button = view.findViewById(R.id.genreSelected_latestBtn)
        val upcomingBtn: Button = view.findViewById(R.id.genreSelected_upcomingBtn)

        bestRatedBtn.setOnClickListener {
            filteredMoviesList.clear()
            moviesAccess.listBestRatedMoviesWithGenres(genres) {
                updateFilteredMoviesList(it)
            }
        }

        popularBtn.setOnClickListener {
            filteredMoviesList.clear()
            moviesAccess.listPopularMoviesWithGenres(genres) {
                updateFilteredMoviesList(it)
            }
        }

        latestBtn.setOnClickListener {
            filteredMoviesList.clear()
            moviesAccess.listLatestMoviesWithGenres(genres) {
                updateFilteredMoviesList(it)
            }
        }

        upcomingBtn.setOnClickListener {
            filteredMoviesList.clear()
            moviesAccess.listUpcomingMoviesWithGenres(genres) {
                updateFilteredMoviesList(it)
            }
        }
    }

    fun updateFilteredMoviesList (movies: List<Result>) {
        movies.forEach { movie ->
            filteredMoviesList.add(movie)

        }

        val moviesAdapter = ListedMoviesAdapter(filteredMoviesList) {
            movieClicked ->
            // TODO
            // LLEVAR A PANTALLA DE DETALLES PELICULA
            Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.vote_average)}", Toast.LENGTH_SHORT).show()
        }

        moviesFilteredRv.adapter = moviesAdapter
    }

    private fun addTrendingMoviesWithGenreToRv(genres: List<Int>) {
        moviesAccess.listTrendingMoviesWithGenres(genres) { result ->
            result.forEach { movie ->
                moviesListTrending.add(movie)
            }

            val moviesAdapter = CarouselMoviesAdapter(moviesListTrending) { movieClicked ->
                Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.vote_average)}", Toast.LENGTH_SHORT).show()
            }

            popularMoviesRv.adapter = moviesAdapter
        }

    }
}