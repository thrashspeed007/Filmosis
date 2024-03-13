package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListedPersonsAdapter
import com.example.filmosis.data.access.tmdb.PersonsAccess
import com.example.filmosis.data.model.tmdb.Person

/**
 * Fragmento que muestra una lista de personas buscadas.
 *
 * @property personsAccess PersonsAccess para realizar consultas a la API de TMDB sobre datos de personas
 * @see PersonsAccess
 *
 * @property rv RecyclerView donde se muestran las personas buscadas
 * @property personsList ArrayList de Personas para guardar las personas buscadas
 *
 */
class PersonsSearchedFragment : Fragment() {
    private val personsAccess = PersonsAccess()
    private lateinit var rv: RecyclerView

    private var personsList: ArrayList<Person> = ArrayList()

    companion object {
        private const val ARG_QUERY = "tmdbQuery"

        /**
         * Método estático para crear una nueva instancia de PersonsSearchedFragment.
         *
         * @param query La cadena de búsqueda de personas.
         * @return Una nueva instancia de PersonsSearchedFragment.
         */
        fun newInstance(query: String): PersonsSearchedFragment {
            val fragment = PersonsSearchedFragment()
            val args = Bundle()

            args.putString(ARG_QUERY, query)

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
        return inflater.inflate(R.layout.fragment_persons_searched, container, false)
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
        val searchView: SearchView = view.findViewById(R.id.personsSearched_searchView)
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

        rv = view.findViewById(R.id.personsSearched_recyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        if (query != null) {
            addSearchedPersonsToRv(query)
        }
    }

    /**
     * Ejecuta la búsqueda de personas mediante la función addSearchedPersonsToRv
     * @see addSearchedPersonsToRv
     *
     * @param query La cadena de búsqueda.
     */
    private fun performSearch(query: String) {
        addSearchedPersonsToRv(query)
    }

    /**
     * Realiza la búsqueda de personas y las agrega en el RecyclerView.
     *
     * @param query La cadena de búsqueda.
     */
    private fun addSearchedPersonsToRv(query: String) {
        personsAccess.searchPerson(query) { result ->
            personsList.clear()

            result.forEach { person ->
                personsList.add(person)
            }

            if (personsList.isEmpty()) {
                Toast.makeText(requireContext(), "No hay resultados", Toast.LENGTH_LONG).show()
            } else {
                val personsAdapter = ListedPersonsAdapter(personsList) { personClicked ->
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PersonDetailsFragment.newInstance(personClicked.id))
                        .addToBackStack(null)
                        .commit()
                }

                rv.adapter = personsAdapter
            }
        }
    }
}