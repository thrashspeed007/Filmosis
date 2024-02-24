package com.example.filmosis.fragments
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import com.example.filmosis.MoviesAdapter
import com.example.filmosis.R
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result

class HomeFragment : Fragment() {
    private val moviesAccess = MoviesAccess()

    private lateinit var rvPopular: RecyclerView
    private lateinit var rvUpcoming: RecyclerView
    private lateinit var rvRecommend: RecyclerView


    private var moviesListPopulares: ArrayList<Result> = ArrayList()
    private var moviesListSoon: ArrayList<Result> = ArrayList()
    private var recommendedMovies: ArrayList<Result> = ArrayList()


    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val saludoUsuTextView: TextView = view.findViewById(R.id.saludoUsu)

        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val username = prefs.getString("username", null)

        saludoUsuTextView.text = "Hi, $username"

        val toolbar: Toolbar = view.findViewById(R.id.homeToolbar)
        // Establecer la barra de herramientas como la barra de soporte de la actividad
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)


        //RecyclerView para las peliculas populares
        rvPopular = view.findViewById(R.id.moviesRecyclerView)
        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvPopular)
        addMoviesToList()

        //RecyclerView para las pelis que saldran proximamente :)
        rvUpcoming = view.findViewById(R.id.moviesSoonRecyclerView)
        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper2: SnapHelper = LinearSnapHelper()
        snapHelper2.attachToRecyclerView(rvUpcoming)
        addMoviesUpComingToList()

        rvRecommend = view.findViewById(R.id.movieRecomendedRecyclerView)
        rvRecommend.setHasFixedSize(true)
        rvRecommend.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper3: SnapHelper = LinearSnapHelper()
        snapHelper3.attachToRecyclerView(rvRecommend)
        addMoviesRecommendedToList()



    }
    private fun addMoviesToList() {
        moviesAccess.listPopularMovies { result ->
            result.forEach { movie ->
                moviesListPopulares.add(movie)
            }

            moviesAdapter = MoviesAdapter(moviesListPopulares) { movieClicked ->
                Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.vote_average)}", Toast.LENGTH_SHORT).show()
            }
            rvPopular.adapter = moviesAdapter
        }

    }

    private fun addMoviesUpComingToList(){
        moviesAccess.listUpcomingMovies { results ->
            results.forEach{movie->
                moviesListSoon.add(movie)
            }

            moviesAdapter = MoviesAdapter(moviesListSoon){movieClicked ->
                Toast.makeText(requireContext(),"Fecha de salida: ${movieClicked.release_date}", Toast.LENGTH_SHORT).show()

            }
            rvUpcoming.adapter = moviesAdapter
        }
    }

    private fun addMoviesRecommendedToList() {
        //aqui le paso el id de una pelicula cualquiera(en este caso he pillado la de DUNE)
        moviesAccess.listRecommendedMovies(movieId=438631) { results ->
//            Log.d("DEBUG", "Results size: ${results.size}")
            results.forEach { movie ->
                recommendedMovies.add(movie)
            }

            moviesAdapter = MoviesAdapter(recommendedMovies) { movieClicked ->
                Toast.makeText(requireContext(), "Informacion de la pelicula: ${movieClicked.release_date} ${movieClicked.vote_average} ${movieClicked.overview}", Toast.LENGTH_SHORT).show()
            }
            rvRecommend.adapter = moviesAdapter
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                Toast.makeText(requireContext(), "Búsqueda", Toast.LENGTH_SHORT).show()

                true
            }
            R.id.notificacion_action->{
                Toast.makeText(requireContext(), "Notificacion", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }
}
