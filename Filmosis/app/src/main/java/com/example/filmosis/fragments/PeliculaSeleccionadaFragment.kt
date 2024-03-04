package com.example.filmosis.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.adapters.PersonasAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Director
import com.example.filmosis.data.model.tmdb.MovieData

class PeliculaSeleccionadaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
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

    private var directores: ArrayList<Director> = ArrayList()
    private val ma = MoviesAccess()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val datosPeli = recuperarDatos(arguments)


        val view = inflater.inflate(R.layout.fragment_pelicula_seleccionada, container, false)

        videoView = view.findViewById(R.id.webView2)

        if (datosPeli != null) {
            ma.getMovieDetails(datosPeli.movieId) { videoUrl ->
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
        }


        //Recyclerview para directores actores...
        recyclerView = view.findViewById(R.id.recyclerPersonas)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL, false)
        val snapHelper3: SnapHelper = LinearSnapHelper()
        snapHelper3.attachToRecyclerView(recyclerView)
        if (datosPeli != null) {
            addDirectoresToList(datosPeli)
        }
        //Datos de la pelicula
//        tvGenero = view.findViewById(R.id.tvGenero)
//        tvGenero.text = datosPeli
        tvCensura = view.findViewById(R.id.tvCensura)
        if (datosPeli != null) {
            if (datosPeli.adult) {
                tvCensura.text =  " +18"
            }else{
                tvCensura.text =  " Todos los publicos"
            }
        }
        tvIdioma = view.findViewById(R.id.tvLenguage)
        if (datosPeli != null) {
            tvIdioma.text = datosPeli.originalLanguage.uppercase()
        }
        tvSinopsis = view.findViewById(R.id.tvSinopsis)
        if (datosPeli != null) {
            tvSinopsis.text = datosPeli.overview
        }
        tvTitle = view.findViewById(R.id.tvTitle)
        if (datosPeli != null) {
            tvTitle.text = datosPeli.title
        }
//        tvTime = view.findViewById(R.id.tvTimeFilm)
//        tvTime.text = datosPeli.

        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)
        if (datosPeli != null) {
            tvReleaseDate.text = datosPeli.releaseDate.toString()
        }
        tvAvg = view.findViewById(R.id.averageVote)
        if (datosPeli != null) {
            val maxRating = 10
            val voteAverage = datosPeli.voteAverage.toFloat()
            val rating = (voteAverage / maxRating) * tvAvg.numStars
            tvAvg.rating = rating
        }


//        image = view.findViewById(R.id.image)
//        if (datosPeli != null) {
//            val uri = Uri.parse(datosPeli.posterPath)
//            image.setImageURI(uri)
//        }

        tvavg = view.findViewById(R.id.tvAvg)
        if (datosPeli != null) {
            tvavg.text = datosPeli.voteAverage.toString()
        };

        ibBack = view.findViewById(R.id.back)
        ibBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }




        return view

    }

    private fun addDirectoresToList(data: MovieData) {
        ma.getDirectorDetails(data.movieId){directors ->
            directors?.forEach{ director ->
                directores.add(director)
            }
            directorAdapter = PersonasAdapter(directores){directorClicked ->
                Toast.makeText(requireContext(), directorClicked.name,Toast.LENGTH_SHORT).show()
            }
            recyclerView.adapter = directorAdapter



        }
    }

    private fun recuperarDatos (bundle: Bundle?): MovieData?{
        if (bundle == null) return null

        // Extrae los datos del Bundle
        val movieId = bundle.getInt("movieId", -1)
        val title = bundle.getString("title", "")
        val overview = bundle.getString("overview", "")
        val popularity = bundle.getDouble("popularity", 0.0)
        val releaseDate = bundle.getString("release_date", "")
        val voteAverage = bundle.getDouble("vote_average", 0.0)
        val voteCount = bundle.getInt("vote_count", 0)
        val adult = bundle.getBoolean("adult", false)
        val backdropPath = bundle.getString("backdrop_path", "")
        val originalLanguage = bundle.getString("original_language", "")
        val originalTitle = bundle.getString("original_title", "")
        val video = bundle.getBoolean("video", false)
        val posterPath = bundle.getString("poster_path", "")

        // retornamos los datos de la pelicula
        return MovieData(
            movieId,
            title,
            overview,
            popularity,
            releaseDate,
            voteAverage,
            voteCount,
            adult,
            backdropPath,
            originalLanguage,
            originalTitle,
            video,
            posterPath,

            )
    }

}
