package com.example.filmosis.fragments

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.CarouselMoviesAdapter
import com.example.filmosis.adapters.GenresCardViewsAdapter
import com.example.filmosis.adapters.ListedMoviesAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Cast
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.utilities.tmdb.TmdbData
import com.example.filmosis.utilities.tmdb.TmdbSearchQueries

/**
 * Un fragmento que representa la sección de Explorar de la aplicación, permitiendo a los usuarios descubrir películas populares, buscar películas o personas, y filtrar películas según diferentes criterios.
 * Este fragmento utiliza RecyclerViews para mostrar películas populares y listas de películas filtradas.
 *
 * @property rootView La vista raíz del fragmento.
 * @property moviesAccess MoviesAccess para realizar consultas a la API de TMDB sobre datos películas
 * @see MoviesAccess
 *
 * @property trendingMovies ArrayList de Películas para almacenar las películas en tendencia / populares
 * @property filteredMoviesList ArrayList de Peliculas para guardar las películas filtradas cada vez que se aplica un nuevo filtro
 * @property trendingMoviesRecyclerView RecyclerView para mostrar las películas en tendencia / populares
 * @property moviesFilteredRv RecyclerView para mostrar las películas filtradas
 */
class ExploreFragment : Fragment() {
    private lateinit var rootView: View

    private val moviesAccess = MoviesAccess()

    private var trendingMovies: ArrayList<Movie> = ArrayList()
    private var filteredMoviesList: ArrayList<Movie> = ArrayList()

    private lateinit var trendingMoviesRecyclerView: RecyclerView
    private lateinit var moviesFilteredRv: RecyclerView

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

    /**
     * Configura los componentes de la vista.
     * @param view La vista raíz del fragmento.
     */
    private fun setup(view: View) {
        trendingMoviesRecyclerView = view.findViewById(R.id.explore_trendingMoviesRecyclerView)
        moviesFilteredRv = view.findViewById(R.id.explore_moviesListsRecyclerView)
        moviesFilteredRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        initMoviesFilteredRv()
        addMoviesTrendingMoviesRv()

        initSearchViewAndSearchFilter(view)
        initGenreCardViews()
        initFilterMoviesButtons(view)
    }

    /**
     * Inicializa el SearchView y el filtro de búsqueda.
     * @param view La vista raíz del fragmento.
     */
    private fun initSearchViewAndSearchFilter(view: View) {
        val searchView: SearchView = view.findViewById(R.id.explore_searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    performSearch(TmdbSearchQueries.MOVIES_SEARCH ,query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        val searchFilterImageButton : ImageButton = view.findViewById(R.id.explore_searchFilterImageButton)
        searchFilterImageButton.setOnClickListener {
            showSearchFilterMenu(searchFilterImageButton)
        }
    }

    /**
     * Realiza una búsqueda utilizando un tipo de búsqueda y un texto de sugerencia especificados.
     * @param searchType El tipo de búsqueda.
     * @param hint El texto de sugerencia para el campo de búsqueda.
     */
    private fun performSearchWithQueryHint(searchType: TmdbSearchQueries, hint: String) {
        val searchView: SearchView = requireView().findViewById(R.id.explore_searchView)
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

    /**
     * Muestra un menú emergente de filtro de búsqueda.
     * @param anchor La vista que actúa como ancla para el menú emergente.
     */
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

    /**
     * Realiza una búsqueda utilizando el tipo de búsqueda y la consulta proporcionados.
     * @param searchType El tipo de búsqueda.
     * @param query La consulta de búsqueda.
     */
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

    /**
     * Agrega las películas populares al RecyclerView de películas populares.
     */
    private fun addMoviesTrendingMoviesRv() {
        moviesAccess.listTrendingMovies { result ->
            trendingMovies.clear()
            result.forEach { movie ->
                trendingMovies.add(movie)
            }

            val moviesAdapter = CarouselMoviesAdapter(trendingMovies) { movieClicked ->
                //flow adrianix

                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(movieClicked.id))
                transaction.addToBackStack(null)
                transaction.commit()
            }

            trendingMoviesRecyclerView.adapter = moviesAdapter
        }
    }

    /**
     * Inicializa las cartas de cada género.
     */
    private fun initGenreCardViews() {
        val genresRecyclerView: RecyclerView = rootView.findViewById(R.id.explore_genresRecyclerView)

        genresRecyclerView.adapter = GenresCardViewsAdapter(TmdbData.movieGenresIds)

        genresRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.HORIZONTAL, false)
    }

    /**
     * Resuelve el color del tema.
     * @param context El contexto de la aplicación.
     * @param attr El atributo de color a resolver.
     * @return El color resuelto.
     */
    private fun resolveThemeColor(context: Context, attr: Int): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }

    /**
     * Inicializa los botones de filtrado de películas.
     * @param view La vista raíz del fragmento.
     */
    private fun initFilterMoviesButtons(view: View) {
        val bestRatedBtn: Button = view.findViewById(R.id.explore_bestRatedBtn)
        val popularBtn: Button = view.findViewById(R.id.explore_popularBtn)
        val latestBtn: Button = view.findViewById(R.id.explore_latestBtn)
        val upcomingBtn: Button = view.findViewById(R.id.explore_upcomingBtn)

        val buttons = listOf(bestRatedBtn, popularBtn, latestBtn, upcomingBtn)

        for (actualButton in buttons) {

            actualButton.setBackgroundColor(resolveThemeColor(requireContext(), androidx.appcompat.R.attr.colorBackgroundFloating))

            actualButton.setOnClickListener {
                if (!actualButton.isSelected) {
                    for (button in buttons) {
                        button.isSelected = false
                        button.setBackgroundColor(resolveThemeColor(requireContext(), androidx.appcompat.R.attr.colorBackgroundFloating))
                    }

                    actualButton.isSelected = true
                    actualButton.setBackgroundColor(resolveThemeColor(requireContext(), androidx.appcompat.R.attr.colorPrimary))

                    filteredMoviesList.clear()
                    when (actualButton.id) {
                        R.id.explore_bestRatedBtn -> moviesAccess.listBestRatedMovies { updateFilteredMoviesList(it) }
                        R.id.explore_popularBtn -> moviesAccess.listPopularMovies { updateFilteredMoviesList(it) }
                        R.id.explore_latestBtn -> moviesAccess.listLatestMovies { updateFilteredMoviesList(it) }
                        R.id.explore_upcomingBtn -> moviesAccess.listUpcomingMovies { updateFilteredMoviesList(it) }
                    }
                }
            }
        }

        bestRatedBtn.isSelected = true
        bestRatedBtn.setBackgroundColor(resolveThemeColor(requireContext(), androidx.appcompat.R.attr.colorPrimary))
    }

    /**
     * Actualiza la lista de películas filtradas.
     * @param movies La lista de películas obtenidas.
     */
    private fun updateFilteredMoviesList (movies: List<Movie>) {

        movies.forEach { movie ->
            filteredMoviesList.add(movie)

        }

        val moviesAdapter = ListedMoviesAdapter(filteredMoviesList) {
                movieClicked ->

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(movieClicked.id))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        moviesFilteredRv.adapter = moviesAdapter
    }

    /**
     * Inicializa el RecyclerView de películas filtradas con las peliculas populares.
     */
    private fun initMoviesFilteredRv() {
        moviesAccess.listBestRatedMovies { updateFilteredMoviesList(it) }
    }
}