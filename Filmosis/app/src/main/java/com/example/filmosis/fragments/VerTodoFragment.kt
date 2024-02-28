package com.example.filmosis.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.GridRecyclerViewAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.dataclass.Pelicula

class VerTodoFragment : Fragment(), GridRecyclerViewAdapter.OnItemClickListener {
    private val moviesAccess = MoviesAccess()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GridRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //populares
        val view = inflater.inflate(R.layout.fragment_ver_todo, container, false)


        recyclerView = view.findViewById(R.id.recyclerViewVerTodo)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        moviesAccess.listPopularMovies { movies ->
            adapter = GridRecyclerViewAdapter(movies)
            recyclerView.adapter = adapter
            adapter.setOnItemClickListener(this@VerTodoFragment)
        }

        //recomendadas
//        val recyclerViewRecomendadas : RecyclerView = view.findViewById(R.id.recyclerVerTodoRecomendadas)
//        recyclerViewRecomendadas.layoutManager = GridLayoutManager(requireContext(), 3)
//        moviesAccess.listRecommendedMovies(movieId=438631) { movies ->
//            adapter = GridRecyclerViewAdapter(movies)
//            recyclerViewRecomendadas.adapter = adapter
//            adapter.setOnItemClickListener(this@VerTodoFragment)
//        }


        val buttonVolver : ImageButton = view.findViewById(R.id.goBack)
        buttonVolver.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onItemClick(movie: Result) {
        // Aquí puedes manejar el clic en la película
        Toast.makeText(requireContext(),"hOLA", Toast.LENGTH_SHORT).show()
    }
}