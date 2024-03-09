package com.example.filmosis.fragments

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
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
import com.example.filmosis.R
import com.example.filmosis.adapters.ActoresAdapter
import com.example.filmosis.adapters.PersonasAdapter
import com.example.filmosis.adapters.ServicioAdapter
import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Genre
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.Moviefr

import com.example.filmosis.dataclass.Servicio
import com.example.filmosis.init.FirebaseInitializer
import com.example.filmosis.utilities.app.ResourcesMapping
import com.example.filmosis.utilities.tmdb.TmdbData
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class PeliculaSeleccionadaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewReparto: RecyclerView


    private lateinit var recyclerViewService: RecyclerView
    private lateinit var recyclerViewServiceAlquiler: RecyclerView
    private lateinit var recyclerViewServiceCompra: RecyclerView
    private lateinit var videoView: WebView
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
    private lateinit var tvPupu : TextView
    private lateinit var ibBack : ImageButton
    lateinit var idioma : String

    private lateinit var textNodispSubs : TextView
    private lateinit var textNodispAlq : TextView
    private lateinit var textNodispComp : TextView



    private var services: HashSet<Servicio> = HashSet()
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
        vote_count = 0,
        spoken_languages = emptyList()
    )
    private var recuperacionInfoGenres: Moviefr = Moviefr(
        adult = false,
        backdrop_path = "",
        genres = emptyList(),
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
        vote_count = 0,
        spoken_languages = emptyList()
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


        textNodispComp = view.findViewById(R.id.textNoDisponibleCompra)
        textNodispAlq= view.findViewById(R.id.textNoDisponibleAlquiler)
        textNodispSubs = view.findViewById(R.id.textNoDisponible)

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
                    recyclerControl(view)
                    datosPeliculas(view)
                    video(view)

                }
            }
            ma.getMovieDataGenres(movieId){movie ->
                if (movie != null) {
                    recuperacionInfoGenres = movie
                    view.findViewById<TextView>(R.id.tvGenero).text = obtenerGeneros(recuperacionInfoGenres.genres)

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

        //SERVICIOS

        recyclerViewService = view.findViewById(R.id.recyclerServiciosPeli)
        recyclerViewServiceAlquiler = view.findViewById(R.id.recyclerServiciosPeliAlquiler)
        recyclerViewServiceCompra = view.findViewById(R.id.recyclerServiciosPeliComprar)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewService)
        val snapHelper2: SnapHelper = LinearSnapHelper()
        snapHelper2.attachToRecyclerView(recyclerViewServiceAlquiler)
        val snapHelper3: SnapHelper = LinearSnapHelper()
        snapHelper3.attachToRecyclerView(recyclerViewServiceCompra)

        addService(requireContext(), recuperacionInfo)


    }

    fun video (view: View){
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

        recuperacionInfo.spoken_languages.firstOrNull()?.let { idioma = it.name }
        tvIdioma.text = idioma

        tvSinopsis = view.findViewById(R.id.tvSinopsis)
        tvSinopsis.text = recuperacionInfo.overview

        tvTitle = view.findViewById(R.id.tvTitle)
        tvTitle.text = recuperacionInfo.title

        tvReleaseDate = view.findViewById(R.id.tvReleaseDate)
        tvReleaseDate.text = recuperacionInfo.release_date

        tvPupu = view.findViewById(R.id.tvPopu)
        tvPupu.text = recuperacionInfo.popularity.toString()

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

        val button =view.findViewById<Button>(R.id.anadirLista)
        button.setOnClickListener {
            addMovieToList(recuperacionInfo)
        }


    }

    private fun obtenerGeneros(genres: List<Genre>): String {
        val genresString = genres.map { it.name }
        return genresString.joinToString(", ")
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

    private fun addService(context: Context, data: Movie) {
        ma.fetchNetworkDetails2(data.id) { response ->
            response?.let { networkDetails ->
                val serviceListSubs = ArrayList<Servicio>()
                val serviceListAlq = ArrayList<Servicio>()
                val serviceListComprar = ArrayList<Servicio>()

                networkDetails.results?.let { results ->

                    results["ES"]?.flatrate?.forEach { servicio ->
                        servicio?.let { serviceListSubs.add(it) }
                    }
                    results["ES"]?.rent?.forEach { servicio ->
                        servicio?.let { serviceListAlq.add(it) }
                    }
                    results["ES"]?.buy?.forEach { servicio ->
                        servicio?.let { serviceListComprar.add(it) }
                    }
                }

                // Mostrar o ocultar los textos según la disponibilidad de servicios
                updateTextViewVisibility(serviceListAlq, textNodispAlq)
                updateTextViewVisibility(serviceListComprar, textNodispComp)
                updateTextViewVisibility(serviceListSubs, textNodispSubs)

                // Configurar adaptadores y administradores de diseño para RecyclerViews
                setupRecyclerView(recyclerViewService, context, serviceListSubs)
                setupRecyclerView(recyclerViewServiceAlquiler, context, serviceListAlq)
                setupRecyclerView(recyclerViewServiceCompra, context, serviceListComprar)
            } ?: kotlin.run {
                Log.e("addService", "Error: La respuesta es nula")
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, context: Context, serviceList: List<Servicio>) {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = ServicioAdapter(serviceList)
    }

    private fun updateTextViewVisibility(serviceList: List<Servicio>, textView: TextView) {
        if (serviceList.isEmpty()) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

    private fun addMovieToList(movie: Movie) {
        val userEmail = FirebaseInitializer.authInstance.currentUser?.email.toString()
        val listsRef = FirebaseInitializer.firestoreInstance.collection("lists").document(userEmail)

        listsRef
            .get()
            .addOnSuccessListener { document ->
                val data = document.data

                val listOptions = ArrayList<String>()
                val listIds = ArrayList<String>()

                data?.forEach { (key, value) ->
                    val listData = value as? Map<*, *>
                    val listId = listData?.get("listId").toString()
                    val listName = listData?.get("listName").toString()

                    listOptions.add(listName)
                    listIds.add(listId)
                }
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Elige una lista")
                    .setItems(listOptions.toTypedArray()) { dialog, which ->
                        val selectedListId = listIds[which]
                        addMovieToSelectedList(movie, selectedListId)
                    }
                    .setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                    }
                builder.create().show()


            }
            .addOnFailureListener { exception ->
                Log.d("Error", "Error fetching lists: $exception")

            }
    }

    private fun addMovieToSelectedList(movie: Movie, desiredListId: String) {
        if (isAdded) {
            // El fragmento está adjunto a una actividad, es seguro llamar a requireContext() aquí
            val userEmail = FirebaseInitializer.authInstance.currentUser?.email.toString()
            val listsRef =
                FirebaseInitializer.firestoreInstance.collection("lists").document(userEmail)

            listsRef.get()
                .addOnSuccessListener { document ->
                    val data = document.data
                    data?.forEach { (key, value) ->
                        val listData = value as? Map<*, *>
                        val listId = listData?.get("listId").toString()

                        if (listId == desiredListId) {
                            val moviesList =
                                document.get("lista_$listId.listMovies") as? MutableList<Map<String, Any>>

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

//
//                    if (document != null) {
//                        val moviesList = document.get("listMovies") as? ArrayList<HashMap<String, Any>>?
//
//                        moviesList?.add(
//                            hashMapOf(
//                                "id" to movie.id,
//                                "title" to movie.title,
//                                "poster_path" to movie.poster_path,
//                                "releaseDate" to movie.release_date,
//                                "averageVote" to movie.vote_average
//                            )
//                        )
//
//                        listDocRef.update("listMovies", moviesList)
//                            .addOnSuccessListener {
//                                requireActivity().onBackPressedDispatcher.onBackPressed()
//                            }
//                            .addOnFailureListener { e ->
//                                Log.e(TAG, "Error updating movies list: $e")
//                                Toast.makeText(requireContext(), "Error updating movies list", Toast.LENGTH_SHORT).show()
//                            }
//                    } else {
//                        Log.e(TAG, "Document not found")
//                    }
//                }
//                .addOnFailureListener { e ->
//                    Log.e(TAG, "Error getting document: $e")
//                    Toast.makeText(requireContext(), "Error getting document", Toast.LENGTH_SHORT).show()
//                }
//        } else {
//            Log.e(TAG, "Fragment not attached to activity")
//        }
                }
        }
    }



    private fun handleAddMovieToListError(exception: Exception) {
        requireActivity().onBackPressedDispatcher.onBackPressed()
        Toast.makeText(requireContext(), "Error al añadir la película a la lista: ${exception.message}", Toast.LENGTH_SHORT).show()
    }



}









