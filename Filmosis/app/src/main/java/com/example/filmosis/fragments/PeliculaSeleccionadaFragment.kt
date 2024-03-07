package com.example.filmosis.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ActoresAdapter
import com.example.filmosis.adapters.PersonasAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Director
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.MovieData

class PeliculaSeleccionadaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewReparto: RecyclerView
    private lateinit var recyclerViewServie: RecyclerView
    private lateinit var videoView: WebView
    private lateinit var directorAdapter: PersonasAdapter
    private lateinit var tvGenero : TextView
    private lateinit var tvCensura : TextView
    private lateinit var tvIdioma : TextView
    private lateinit var tvSinopsis : TextView
    private lateinit var tvTitle : TextView
    private lateinit var tvTime : TextView
    private lateinit var tvReleaseDate : TextView
    private lateinit var tvAvg : RatingBar
    private lateinit var image : ImageView
    private lateinit var tvavg : TextView
    private lateinit var ibBack : ImageButton
    private lateinit var seleccionada : PeliculaSeleccionadaFragment

    private var directores:  ArrayList<Director> = ArrayList()
    private val ma = MoviesAccess()
    private var recuperacionInfo: Movie = Movie(
        adult = false,
        backdrop_path = "",
        genre_ids = emptyList(),
        id = 0,
        original_language = "",
        original_title = "",
        overview = "",
        popularity = 0.0,
        poster_path = "",
        release_date = "",
        title = "",
        video = false,
        vote_average = 0.0,
        vote_count = 0
    )




    companion object {
        private const val ARG_MOVIE_ID = "movieId"

        fun newInstance(movieId: Int): PeliculaSeleccionadaFragment {
            val fragment = PeliculaSeleccionadaFragment()
            val args = Bundle()

            args.putInt(ARG_MOVIE_ID, movieId)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pelicula_seleccionada, container, false)
        recuperarDatosInfo(view)
//        videoView = view.findViewById(R.id.webView2)
//
//        ma.getMovieDetails(recuperacionInfo.id) { videoUrl ->
//            if (videoUrl != null) {
//                val videoIframe = "<iframe width=\"100%\" height=\"100%\" src=\"$videoUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
//                videoView.settings.javaScriptEnabled = true
//                videoView.webChromeClient = WebChromeClient()
//                videoView.loadData(videoIframe,"text/html","utf-8")
//
//
//            } else {
//                val textView = view.findViewById<TextView>(R.id.errorvideo)
//                textView.text = "El video no está disponible"
//
//            }
//        }
//
//
//        //Recyclerview para directores
//        recyclerView = view.findViewById(R.id.recyclerPersonas)
//        recyclerView.setHasFixedSize(true)
//        addDirectoresToList(requireContext(), recuperacionInfo)
//
//        //reparto
//        recyclerViewReparto = view.findViewById(R.id.recyclerActores)
//        recyclerViewReparto.setHasFixedSize(true)
//        addActoresToList(requireContext(), recuperacionInfo)
//
//        //Datos de la pelicula
//        tvCensura = view.findViewById(R.id.tvCensura)
//        if (recuperacionInfo.adult) {
//            tvCensura.text = " +18"
//        } else {
//            tvCensura.text = " Todos los públicos"
//        }
//        tvIdioma = view.findViewById(R.id.tvLenguage)
//        tvIdioma.text = recuperacionInfo.original_language?.uppercase()
//
//        tvSinopsis = view.findViewById(R.id.tvSinopsis)
//        tvSinopsis.text = recuperacionInfo.overview
//
//        tvTitle = view.findViewById(R.id.tvTitle)
//        tvTitle.text = recuperacionInfo.title
//
//        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)
//        tvReleaseDate.text = recuperacionInfo.release_date
//
//        tvAvg = view.findViewById(R.id.averageVote)
//        val maxRating = 10
//        val voteAverage = recuperacionInfo.vote_average
//        val rating = (voteAverage / maxRating) * tvAvg.numStars
//        tvAvg.rating = rating.toFloat()
//
//        tvavg = view.findViewById(R.id.tvAvg)
//        tvavg.text = recuperacionInfo.vote_average?.toString() ?: ""
//
//        ibBack = view.findViewById(R.id.back)
//        ibBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }

        return view

    }

    private fun addDirectoresToList(context: Context, data: Movie) {
        ma.getDirectorDetails(data.id) { directors ->
            directors?.let { directorList ->
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = PersonasAdapter(directorList) { directorClicked ->

                    val fragmentManager = requireActivity().supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, PersonDetailsFragment.newInstance(directorClicked.id))
                    transaction.addToBackStack(null)
                    transaction.commit()

                }
            }
        }
    }


    private fun recuperarDatosInfo(view: View) {
        val movieId = arguments?.getInt(ARG_MOVIE_ID)

        if (movieId != null) {
            ma.getMovieData(movieId) { movie ->
                if (movie != null) {
                    recuperacionInfo = movie

                    videoView = view.findViewById(R.id.webView2)

                    ma.getMovieDetails(recuperacionInfo.id) { videoUrl ->
                        if (videoUrl != null) {
                            val videoIframe = "<iframe width=\"100%\" height=\"100%\" src=\"$videoUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
                            videoView.settings.javaScriptEnabled = true
                            videoView.webChromeClient = WebChromeClient()
                            videoView.loadData(videoIframe,"text/html","utf-8")


                        } else {
                            val textView = view.findViewById<TextView>(R.id.errorvideo)
                            textView.text = "El video no está disponible"

                        }
                    }


                    recyclerControl(view)
                    datosPeliculas(view)

                }
            }
        }
    }

    fun recyclerControl(view: View){
        //Recyclerview para directores
        recyclerView = view.findViewById(R.id.recyclerPersonas)
        recyclerView.setHasFixedSize(true)
        addDirectoresToList(requireContext(), recuperacionInfo)

        //reparto
        recyclerViewReparto = view.findViewById(R.id.recyclerActores)
        recyclerViewReparto.setHasFixedSize(true)
        addActoresToList(requireContext(), recuperacionInfo)

    }

    fun datosPeliculas(view: View){
        //Datos de la pelicula
        tvCensura = view.findViewById(R.id.tvCensura)
        if (recuperacionInfo.adult) {
            tvCensura.text = " +18"
        } else {
            tvCensura.text = " Todos los públicos"
        }
        tvIdioma = view.findViewById(R.id.tvLenguage)
        tvIdioma.text = recuperacionInfo.original_language?.uppercase()

        tvSinopsis = view.findViewById(R.id.tvSinopsis)
        tvSinopsis.text = recuperacionInfo.overview

        tvTitle = view.findViewById(R.id.tvTitle)
        tvTitle.text = recuperacionInfo.title

        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)
        tvReleaseDate.text = recuperacionInfo.release_date

        tvAvg = view.findViewById(R.id.averageVote)
        val maxRating = 10
        val voteAverage = recuperacionInfo.vote_average
        val rating = (voteAverage / maxRating) * tvAvg.numStars
        tvAvg.rating = rating.toFloat()

        tvavg = view.findViewById(R.id.tvAvg)
        tvavg.text = recuperacionInfo.vote_average?.toString() ?: ""

        ibBack = view.findViewById(R.id.back)
        ibBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }


    private fun addActoresToList(context: Context, data: Movie) {
        ma.getActorDetails(data.id) { actores ->
            actores?.let { actorList ->
                recyclerViewReparto.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewReparto.adapter = ActoresAdapter(actorList) { actorClicked ->
                    val fragmentManager = requireActivity().supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, PersonDetailsFragment.newInstance(actorClicked.id))
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
    }




}



