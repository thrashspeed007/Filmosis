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
import com.example.filmosis.adapters.ListedMoviesAdapter
import com.example.filmosis.data.access.tmdb.PersonsAccess
import com.example.filmosis.data.model.tmdb.Person

class DirectorsSearchedFragment : Fragment() {
    private val personsAccess = PersonsAccess()
    private lateinit var rv: RecyclerView

    private var directorsList: ArrayList<Person> = ArrayList()

    companion object {
        private const val ARG_QUERY = "tmdbQuery"

        fun newInstance(query: String): DirectorsSearchedFragment {
            val fragment = DirectorsSearchedFragment()
            val args = Bundle()

            args.putString(ARG_QUERY, query)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_directors_searched, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        val query = arguments?.getString(ARG_QUERY)
        val searchView: SearchView = view.findViewById(R.id.directorsSearched_searchView)
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

        rv = view.findViewById(R.id.directorsSearched_recyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        if (query != null) {
            addSearchedDirectorsToRv(query)
        }
    }

    private fun performSearch(query: String) {
        addSearchedDirectorsToRv(query)
    }

    private fun addSearchedDirectorsToRv(query: String) {
        personsAccess.searchPerson(query) { result ->
            directorsList.clear()

            result.forEach { director ->
                directorsList.add(director)
            }

            if (directorsList.isEmpty()) {
                Toast.makeText(requireContext(), "No hay resultados", Toast.LENGTH_LONG).show()
            } else {
                // TODO
                // CREAR ADAPTADOR DE LISTAS DE PERSONAS...
//                val directorsAdapter = ListedPersonsAdapter(directorsList) { personClicked ->
//                    // TODO
//                    // LLEVAR A PANTALLA DE ACTIVIDAD DE DETALLES DIRECTOR
//                }
//
//                rv.adapter = directorsAdapter
            }
        }
    }
}