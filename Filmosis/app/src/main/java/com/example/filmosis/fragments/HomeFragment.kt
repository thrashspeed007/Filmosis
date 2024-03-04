package com.example.filmosis.fragments
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import com.example.filmosis.R
import com.example.filmosis.adapters.MoviesAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.dataclass.Servicio
import com.example.filmosis.adapters.ServicioAdapter
import com.example.filmosis.utilities.tmdb.TmdbSearchQueries
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {
    private val moviesAccess = MoviesAccess()

    private lateinit var rvPopular: RecyclerView
    private lateinit var rvUpcoming: RecyclerView
    private lateinit var rvRecommend: RecyclerView

    private var moviesListPopulares: ArrayList<Movie> = ArrayList()
    private var moviesListSoon: ArrayList<Movie> = ArrayList()
    private var recommendedMovies: ArrayList<Movie> = ArrayList()
//    private lateinit var tvRecom: TextView
//    private lateinit var tvProx: TextView
//    private lateinit var tvPopu: TextView


    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var scrollView: ScrollView


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
        val saludoUsuTextView: TextView = view.findViewById(R.id.home_tvUsuario)

        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val username = prefs.getString("username", null)

        initSearchViewAndSearchFilter(view)

        //        scrollView.findViewById<ScrollView>(R.id.scrollViewVerTodo)

        saludoUsuTextView.text = "$username"

        //RecyclerView para las peliculas populares
        rvPopular = view.findViewById(R.id.moviesRecyclerView)
        //para mejorar el rendimiento, le indicamos que el tamano del contenido no cambiara
        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        //cuando se desplaza se ajustan automaticamente
        snapHelper.attachToRecyclerView(rvPopular)
        addMoviesToList()

        //RecyclerView para las pelis que saldran proximamente :)
        rvUpcoming = view.findViewById(R.id.moviesSoonRecyclerView)
        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper2: SnapHelper = LinearSnapHelper()
        snapHelper2.attachToRecyclerView(rvUpcoming)
        addMoviesUpComingToList()

        //RecyclerView para las pelis recomendadas :)
        rvRecommend = view.findViewById(R.id.movieRecomendedRecyclerView)
        rvRecommend.setHasFixedSize(true)
        rvRecommend.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val snapHelper3: SnapHelper = LinearSnapHelper()
        snapHelper3.attachToRecyclerView(rvRecommend)
        addMoviesRecommendedToList()

        //servicios
        val recyclerView: RecyclerView = view.findViewById(R.id.serviciosRecyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val servicios = listOf(
            Servicio(R.drawable.ic_netflix, "Netflix"),
            Servicio(R.drawable.ic_movistar, "Movistar Plus"),
            Servicio(R.drawable.ic_amazon_prime, "Amazon Prime"),
            Servicio(R.drawable.ic_hbomax, "HBO max")

        )

        val adapter = ServicioAdapter(requireContext(), servicios)
        recyclerView.adapter = adapter

        //navegar
        val buttonPopu : MaterialButton = view.findViewById(R.id.buttonShowAllPopulares)
        buttonPopu.setOnClickListener {

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val nuevoFragmento = VerTodoFragment()
            transaction.replace(R.id.homeFragment, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
//            scrollToSection(tvPopu)

        }

        val buttonRecom : MaterialButton = view.findViewById(R.id.buttonShowAllRecomendaciones)
        buttonRecom.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val nuevoFragmento = VerTodoFragment()
            transaction.replace(R.id.homeFragment, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
//            scrollToSection(tvRecom)


        }

        val button : MaterialButton = view.findViewById(R.id.buttonShowAllProximamente)
        button.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val nuevoFragmento = VerTodoFragment()
            transaction.replace(R.id.homeFragment, nuevoFragmento)
            transaction.addToBackStack(null)
            transaction.commit()
//            scrollToSection(tvProx)


        }
    }

    private fun initSearchViewAndSearchFilter(view: View) {
        val searchView: SearchView = view.findViewById(R.id.home_searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    performSearch(TmdbSearchQueries.MOVIES_SEARCH, query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        val searchFilterImageButton : ImageButton = view.findViewById(R.id.home_searchFilterImageButton)
        searchFilterImageButton.setOnClickListener {
            showSearchFilterMenu(searchFilterImageButton)
        }
    }

    private fun performSearchWithQueryHint(searchType: TmdbSearchQueries, hint: String) {
        val searchView: SearchView = requireView().findViewById(R.id.home_searchView)
        searchView.queryHint = hint
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String?): Boolean {
                if (!queryText.isNullOrBlank()) {
                    performSearch(searchType, queryText)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showSearchFilterMenu(anchor: View) {
        val popupMenu = PopupMenu(requireContext(), anchor)
        popupMenu.menuInflater.inflate(R.menu.search_filter_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchFilterMenu_searchMovies -> {
                    performSearchWithQueryHint(TmdbSearchQueries.MOVIES_SEARCH, "Buscar películas")
                    true
                }
                R.id.searchFilterMenu_searchDirectors -> {
                    performSearchWithQueryHint(TmdbSearchQueries.DIRECTORS_SEARCH, "Buscar directores")
                    true
                }
                R.id.searchFilterMenu_searchActors -> {
                    performSearchWithQueryHint(TmdbSearchQueries.ACTORS_SEARCH, "Buscar actores")
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun performSearch(searchType: TmdbSearchQueries, query: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        when (searchType) {
            TmdbSearchQueries.MOVIES_SEARCH -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MoviesSearchedFragment.newInstance(query))
                    .addToBackStack(null)
                    .commit()
            }
            TmdbSearchQueries.DIRECTORS_SEARCH -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, DirectorsSearchedFragment.newInstance(query))
                    .addToBackStack(null)
                    .commit()
            }
            TmdbSearchQueries.ACTORS_SEARCH -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ActorsSearchedFragment.newInstance(query))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun scrollToSection(textView:TextView) {
        scrollView.post {
            scrollView.smoothScrollTo(0, textView.top)
        }
    }

    private fun addMoviesToList() {
        moviesAccess.listPopularMovies { result ->
            result.forEach { movie ->
                moviesListPopulares.add(movie)
            }

            moviesAdapter = MoviesAdapter(moviesListPopulares) { movieClicked ->
                Toast.makeText(requireContext(), "Puntuación media: ${(movieClicked.video)}", Toast.LENGTH_SHORT).show()
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

}
