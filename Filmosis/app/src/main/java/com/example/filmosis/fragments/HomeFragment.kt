package com.example.filmosis.fragments
import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

import com.example.filmosis.R
import com.example.filmosis.adapters.CarouselMoviesAdapter
import com.example.filmosis.adapters.ServicioAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.dataclass.NetworkDetailsResponse

import com.example.filmosis.dataclass.Servicio
import com.example.filmosis.network.interfaces.TmdbApiInterface
import com.example.filmosis.utilities.tmdb.TmdbSearchQueries
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {
    private val moviesAccess = MoviesAccess()

    private lateinit var rvPopular: RecyclerView
    private lateinit var rvUpcoming: RecyclerView
    private lateinit var rvRecommend: RecyclerView
    private lateinit var rvServicios: RecyclerView

    private var moviesListPopulares: ArrayList<Movie> = ArrayList()
    private var moviesListSoon: ArrayList<Movie> = ArrayList()
    private var recommendedMovies: ArrayList<Movie> = ArrayList()
    private var services: ArrayList<NetworkDetailsResponse> = ArrayList()
//    private lateinit var tvRecom: TextView
//    private lateinit var tvProx: TextView
//    private lateinit var tvPopu: TextView


    private lateinit var moviesAdapter: CarouselMoviesAdapter

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
        addMoviesToList()

        //RecyclerView para las pelis que saldran proximamente :)
        rvUpcoming = view.findViewById(R.id.moviesSoonRecyclerView)
        rvUpcoming.setHasFixedSize(true)
        addMoviesUpComingToList()

        //RecyclerView para las pelis recomendadas :)
        rvRecommend = view.findViewById(R.id.movieRecomendedRecyclerView)
        rvRecommend.setHasFixedSize(true)
        addMoviesRecommendedToList()

        //servicios
        rvServicios = view.findViewById(R.id.serviciosRecyclerview)
        rvServicios.setHasFixedSize(true)
        servicios()








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
                R.id.searchFilterMenu_searchPersons -> {
                    performSearchWithQueryHint(TmdbSearchQueries.PERSONS_SEARCH, "Buscar personas")
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
            TmdbSearchQueries.PERSONS_SEARCH -> {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, PersonsSearchedFragment.newInstance(query))
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
            moviesListPopulares.clear()
            result.forEach { movie ->
                moviesListPopulares.add(movie)
            }

            rvPopular.adapter = CarouselMoviesAdapter(moviesListPopulares) { movieClicked ->

                onItemClick(movieClicked)
            }
        }

    }
    private fun addMoviesUpComingToList(){
        moviesAccess.listUpcomingMovies { results ->
            moviesListSoon.clear()
            results.forEach{movie->
                moviesListSoon.add(movie)
            }

            rvUpcoming.adapter = CarouselMoviesAdapter(moviesListSoon){movieClicked ->
                onItemClick(movieClicked)
            }
        }
    }

    private fun addMoviesRecommendedToList() {
        //aqui le paso el id de una pelicula cualquiera(en este caso he pillado la de DUNE)
        moviesAccess.listRecommendedMovies(movieId=438631) { results ->
            recommendedMovies.clear()
            results.forEach { movie ->
                recommendedMovies.add(movie)
            }

            rvRecommend.adapter = CarouselMoviesAdapter(recommendedMovies) { movieClicked ->
                onItemClick(movieClicked)
            }
        }
    }

    private fun servicios() {
        moviesAccess.fetchNetworkDetails(networkId = 12200) { result ->
            if (result != null) {
                services.clear()
                services.add(result)

                rvServicios.adapter = ServicioAdapter(services)
            } else {
                println("nada")
            }
        }
    }





    fun onItemClick(movie: Movie) {

        val bundle = Bundle().apply {
            putInt("movieId", movie.id)
            putString("title", movie.title)
            putString("overview", movie.overview)
            putDouble("popularity", movie.popularity)
            putString("release_date", movie.release_date)
            putDouble("vote_average", movie.vote_average)
            putInt("vote_count", movie.vote_count)
            putBoolean("adult", movie.adult)
            putString("backdrop_path", movie.backdrop_path)
            putString("original_language", movie.original_language)
            putString("original_title", movie.original_title)
            putBoolean("video", movie.video)
            putString("poster_path", movie.poster_path)


        }


        //Me llevo la inforamcion para luego recuperarlo en el onCreateView del fragment peliculaseleccionada
        val nuevoFragmento = PeliculaSeleccionadaFragment().apply {
            arguments = bundle
        }

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.homeFragment, nuevoFragmento)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
