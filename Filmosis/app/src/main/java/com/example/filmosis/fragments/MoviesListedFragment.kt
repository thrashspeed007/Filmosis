package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.utilities.tmdb.TmdbQuery
import org.checkerframework.checker.units.qual.A
import java.io.Serializable

class MoviesListedFragment : Fragment() {
    private val moviesAccess = MoviesAccess()

    private var moviesList: ArrayList<Result> = ArrayList()

    companion object {
        private const val ARG_LIST_NAME = "listName"
        private const val ARG_TMDB_QUERY = "tmdbQuery"
        private const val ARG_QUERY_PARAMETERS = "queryParameters"

        fun newInstance(listName: String, tmdbQuery: TmdbQuery, queryParameters: Map<String, Any>? = null): GenreSelectedFragment {
            val fragment = GenreSelectedFragment()
            val args = Bundle()
            args.putString(ARG_LIST_NAME, listName)
            args.putSerializable(ARG_TMDB_QUERY, tmdbQuery)

            if (queryParameters != null) {
                args.putSerializable(ARG_QUERY_PARAMETERS, queryParameters as Serializable)
            }

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
        val listName = arguments?.getString(MoviesListedFragment.ARG_LIST_NAME) ?: ""
        val tmdbQuery = arguments?.getSerializable(MoviesListedFragment.ARG_TMDB_QUERY) as? TmdbQuery
        val queryParameters = arguments?.getSerializable(ARG_QUERY_PARAMETERS) as? Map<*, *>


    }

    private fun getMovieList(query: TmdbQuery) {
        when (query) {
            TmdbQuery.LIST_POPULAR -> moviesAccess.listPopularMovies(::addQueryResultToList)
            TmdbQuery.LIST_UPCOMING -> moviesAccess.listUpcomingMovies(::addQueryResultToList)
            // TODO
            // PASAR ARGUMENTOS RECOGIDOS DEL BUNDLE...
            TmdbQuery.LIST_RECOMMENDED -> moviesAccess.listRecommendedMovies(1, ::addQueryResultToList)
            TmdbQuery.SEARCH_MOVIE -> moviesAccess.searchMovie("XD", ::addQueryResultToList)
        }
    }

    private fun addQueryResultToList(results: List<Result>) {
        // TODO
        // AÑADIR A LA LISTA DEL RECYCLER..
    }
}