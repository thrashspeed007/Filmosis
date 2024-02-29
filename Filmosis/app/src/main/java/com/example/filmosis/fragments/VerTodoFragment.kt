package com.example.filmosis.fragments



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.GridRecyclerViewAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Result


class VerTodoFragment : Fragment(), GridRecyclerViewAdapter.OnItemClickListener {
    private val moviesAccess = MoviesAccess()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewRecomendados: RecyclerView
    private lateinit var recyclerViewProximamentes: RecyclerView
    private lateinit var adapter: GridRecyclerViewAdapter

//    private lateinit var scrollView: ScrollView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_ver_todo, container, false)
//        scrollView = view.findViewById(R.id.scrollViewVerTodo)

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

        //proximamente
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



        // Busca la vista por su ID en el layout de HomeFragment
//        val viewB: View? = activity?.findViewById(R.id.homeFragment)
//        viewB?.let {
//            // Accedemos a la vista de HomeFragment y realizamos ir a la seccion de las pelicualas
//            //populares
//            val buttonVerTodoPopulares: MaterialButton = viewB.findViewById(R.id.buttonShowAllPopulares)
//            buttonVerTodoPopulares.setOnClickListener {
//                scrollToSection(R.id.tvPopulares)
////                Log.d("DEBUG", "Entro??????")
//                println("Spursito xupala hermani")
//            }
//
//            //proximamente
//            val buttonVerTodoProximamente : MaterialButton = viewB.findViewById(R.id.buttonShowAllProximamente)
//            buttonVerTodoProximamente.setOnClickListener{
//                scrollToSection(R.id.tvProximamente)
//            }
//
//            //recomendados
//            val buttonVerTodoRecomendados : MaterialButton = viewB.findViewById(R.id.buttonShowAllRecomendaciones)
//            buttonVerTodoRecomendados.setOnClickListener{
//                scrollToSection(R.id.tvRecomendados)
//            }




        //}


        return view
    }

    //Desplazamiento suave a la seccion deseada
//    private fun scrollToSection(sectionId: Int) {
//        val sectionView = view?.findViewById<View>(sectionId)
//        sectionView?.let { view ->
//            scrollView.post {
//                Log.d("DEBUG", "Entro??????")
//                scrollView.smoothScrollTo(0, view.top)
//            }
//        }
////        scrollView.post(Runnable {scrollView.scrollTo(0,view.scrollY)  })
////
////        sView.post(Runnable { sView.scrollTo(sViewX, sViewY) })
//    }
    override fun onItemClick(movie: Result) {
        val movieId = movie.id
        val bundle = Bundle().apply {
            putInt("movieId", movieId)
        }

        //Me llevo la inforamcion para luego recuperarlo en el onCreateView del fragment peliculaseleccionada
        val nuevoFragmento = PeliculaSeleccionadaFragment().apply {
            arguments = bundle
        }

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_verTodo, nuevoFragmento)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}