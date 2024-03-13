package com.example.filmosis.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListedMoviesAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.init.FirebaseInitializer

/**
 * Fragmento que muestra una lista de películas buscadas o populares.
 *
 * @property moviesAccess MoviesAccess para realizar consultas a la API de TMDB sobre datos de películas
 * @see MoviesAccess
 */
class MoviesSearchedFragment : Fragment() {

    private val moviesAccess = MoviesAccess()
    private lateinit var rv: RecyclerView
    private var moviesList: ArrayList<Movie> = ArrayList()

    companion object {
        private const val ARG_QUERY = "tmdbQuery"
        private const val ARG_ADD_TO_LIST = "performAddToList"
        private const val ARG_LIST_ID = "listId"

        /**
         * Método estático para crear una nueva instancia de MoviesSearchedFragment.
         *
         * @param query La cadena de búsqueda de películas.
         * @param performAddToList Indica si la búsqueda se realiza para agregar películas a una lista.
         * @param listId El ID de la lista a la que se agregarán las películas (solo si performAddToList es true).
         * @return Una nueva instancia de MoviesSearchedFragment.
         */
        fun newInstance(query: String, performAddToList: Boolean = false, listId: String = ""): MoviesSearchedFragment {
            val fragment = MoviesSearchedFragment()
            val args = Bundle()

            args.putString(ARG_QUERY, query)
            args.putBoolean(ARG_ADD_TO_LIST, performAddToList)
            args.putString(ARG_LIST_ID, listId)

            fragment.arguments = args
            return fragment
        }
    }

    /**
     * Crea y devuelve la jerarquía de vistas asociada con el fragmento.
     *
     * @param inflater El LayoutInflater que se utiliza para inflar la vista.
     * @param container El ViewGroup en el que se inflará la vista.
     * @param savedInstanceState Bundle que contiene el estado previamente guardado del fragmento, si lo hay.
     * @return La vista raíz del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_searched, container, false)
    }

    /**
     * Llamado inmediatamente después de que onCreateView() haya creado la jerarquía de vistas del fragmento.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState Bundle que contiene el estado previamente guardado del fragmento, si lo hay.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    /**
     * Configura el fragmento.
     *
     * @param view La vista raíz del fragmento.
     */
    private fun setup(view: View) {
        val query = arguments?.getString(ARG_QUERY)
        val searchView: SearchView = view.findViewById(R.id.moviesSearched_searchView)
        searchView.setQuery(query, false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    performSearch(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        rv = view.findViewById(R.id.moviesSearched_recyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        if (query.isNullOrEmpty()) {
            loadPopularMovies()
        } else {
            addSearchedMoviesToRv(query)
        }
    }

    /**
     * Realiza la búsqueda de películas y las muestra en el RecyclerView.
     *
     * @param query La cadena de búsqueda.
     */
    private fun performSearch(query: String) {
        addSearchedMoviesToRv(query)
        hideKeyboard()
    }

    /**
     * Oculta el teclado virtual.
     */
    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    /**
     * Carga las películas populares y las muestra en el RecyclerView.
     */
    private fun loadPopularMovies() {
        moviesAccess.listPopularMovies { movies ->
            moviesList.addAll(movies)
            rv.adapter = ListedMoviesAdapter(moviesList) { movie ->
                handleMovieClick(movie)
            }
        }
    }

    /**
     * Agrega las películas buscadas al RecyclerView.
     *
     * @param query La cadena de búsqueda.
     */
    private fun addSearchedMoviesToRv(query: String) {
        moviesAccess.searchMovie(query) { result ->
            moviesList.clear()
            moviesList.addAll(result)

            if (moviesList.isEmpty()) {
                showToast("No hay resultados")
            } else {
                rv.adapter = ListedMoviesAdapter(moviesList) { movie ->
                    handleMovieClick(movie)
                }
            }
        }
    }

    /**
     * Muestra un mensaje Toast.
     *
     * @param message El mensaje a mostrar.
     */
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    /**
     * Maneja el clic en una película.
     *
     * @param movie La película seleccionada.
     */
    private fun handleMovieClick(movie: Movie) {
        if (arguments?.getBoolean(ARG_ADD_TO_LIST) == true) {
            showAddToListDialog(movie)
        } else {
            navigateToMovieDetailFragment(movie.id)
        }
    }

    /**
     * Muestra un cuadro de diálogo para confirmar la adición de una película a una lista.
     *
     * @param movie La película a añadir.
     */
    private fun showAddToListDialog(movie: Movie) {
        val listId: String? = arguments?.getString(ARG_LIST_ID)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmación")
        builder.setMessage("¿Añadir ${movie.title} a la lista?")
            .setPositiveButton("Aceptar") { dialog, id ->
                listId?.let { addMovieToList(movie, it) }
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    /**
     * Navega al fragmento de detalles de la película.
     *
     * @param movieId El ID de la película.
     */
    private fun navigateToMovieDetailFragment(movieId: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(movieId))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * Agrega una película a una lista en Firestore.
     *
     * @param movie La película a añadir.
     * @param desiredListId El ID de la lista.
     */
    private fun addMovieToList(movie: Movie, desiredListId: String) {
        val userEmail = FirebaseInitializer.authInstance.currentUser?.email.toString()
        val listsRef = FirebaseInitializer.firestoreInstance.collection("lists").document(userEmail)

        listsRef
            .get()
            .addOnSuccessListener { document ->
                val data = document.data

                data?.forEach { (key, value) ->
                    val listData = value as? Map<*, *>
                    val listId = listData?.get("listId").toString()

                    if (listId == desiredListId) {
                        val moviesList = document.get("lista_$listId.listMovies") as? MutableList<Map<String, Any>>

                        moviesList?.add(
                            mapOf(
                                "id" to movie.id,
                                "title" to movie.title,
                                "poster_path" to movie.poster_path,
                                "releaseDate" to movie.release_date,
                                "averageVote" to movie.vote_average
                            )
                        )

                        listsRef.update("lista_$listId.listMovies", moviesList)
                            .addOnSuccessListener {
                                requireActivity().onBackPressedDispatcher.onBackPressed()
                            }
                            .addOnFailureListener { exception ->
                                handleAddMovieToListError(exception)
                            }
                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.d("bruh", "Error fetching lists: $exception")
            }
    }

    /**
     * Maneja un error al añadir una película a una lista.
     *
     * @param exception La excepción ocurrida.
     */
    private fun handleAddMovieToListError(exception: Exception) {
        requireActivity().onBackPressedDispatcher.onBackPressed()
        Toast.makeText(requireContext(), "Error al añadir la película a la lista: ${exception.message}", Toast.LENGTH_SHORT).show()
    }
}
