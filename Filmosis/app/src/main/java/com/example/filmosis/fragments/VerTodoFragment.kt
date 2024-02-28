package com.example.filmosis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.GridRecyclerViewAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.google.android.material.button.MaterialButton

class VerTodoFragment : Fragment(), GridRecyclerViewAdapter.OnItemClickListener {
    private val moviesAccess = MoviesAccess()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewRecomendados: RecyclerView
    private lateinit var recyclerViewProximamentes: RecyclerView
    private lateinit var adapter: GridRecyclerViewAdapter

    private lateinit var scrollView: ScrollView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_ver_todo, container, false)
        scrollView = view.findViewById(R.id.scrollViewVerTodo)

        //populares
        recyclerView = view.findViewById(R.id.recyclerViewVerTodo)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        moviesAccess.listPopularMovies { movies ->
            adapter = GridRecyclerViewAdapter(movies)
            recyclerView.adapter = adapter
            adapter.setOnItemClickListener(this@VerTodoFragment)
        }

        //recomendadas
        recyclerViewRecomendados = view.findViewById(R.id.recyclerViewVerTodoRecomendados)
        recyclerViewRecomendados.layoutManager = GridLayoutManager(requireContext(), 3)
        moviesAccess.listRecommendedMovies(movieId = 438631) { movies ->
            adapter = GridRecyclerViewAdapter(movies)
            recyclerViewRecomendados.adapter = adapter
            adapter.setOnItemClickListener(this@VerTodoFragment)
        }

        //proimamente
        recyclerViewProximamentes = view.findViewById(R.id.recyclerViewVerTodoProximamente)
        recyclerViewProximamentes.layoutManager = GridLayoutManager(requireContext(), 3)
        moviesAccess.listUpcomingMovies { movies ->
            adapter = GridRecyclerViewAdapter(movies)
            recyclerViewProximamentes.adapter = adapter
            adapter.setOnItemClickListener(this@VerTodoFragment)
        }


        val buttonVolver: ImageButton = view.findViewById(R.id.goBack)
        buttonVolver.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

//        val fragmentB: HomeFragment? =
//            requireFragmentManager().findFragmentByTag("homeFragment") as HomeFragment?
//
//
//
//        //inflamos la vista
//        //populares
//        val buttonVerTodoPopulares: MaterialButton = fragmentB?.view.findViewById(R.id.buttonShowAllPopulares)
//        buttonVerTodoPopulares.setOnClickListener {
//            scrollToSection(R.id.tvPopulares)
//        }
//        //proximamente
//        val buttonVerTodoProximamente : MaterialButton = view.findViewById(R.id.buttonShowAllProximamente)
//        buttonVerTodoProximamente.setOnClickListener{
//            scrollToSection(R.id.tvProximamente)
//        }
//        //recomendados
//        val buttonVerTodoRecomendados : MaterialButton = view.findViewById(R.id.buttonShowAllRecomendaciones)
//        buttonVerTodoRecomendados.setOnClickListener{
//            scrollToSection(R.id.tvRecomendados)
//        }






        return view
    }

    //Desplazamiento suave a la seccion deseada
    private fun scrollToSection(sectionId: Int) {
        val sectionView = view?.findViewById<View>(sectionId)
        sectionView?.let { view ->
            scrollView.post {
                scrollView.smoothScrollTo(0, view.top)
            }
        }
    }
    override fun onItemClick(movie: Result) {
        // Aquí puedes manejar el clic en la película
        Toast.makeText(requireContext(), "hOLA", Toast.LENGTH_SHORT).show()
    }
}