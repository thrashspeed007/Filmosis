package com.example.filmosis.fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.filmosis.R
import com.example.filmosis.data.access.tmdb.MoviesAccess

class PeliculaSeleccionadaFragment : Fragment() {

    private lateinit var videoView: VideoView
    private val ma = MoviesAccess()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieId = arguments?.getInt("movieId", -1) ?: -1
        val view = inflater.inflate(R.layout.fragment_pelicula_seleccionada, container, false)
        val tv : EditText = view.findViewById(R.id.editTextText)



        videoView = view.findViewById(R.id.videoView)

        ma.getMovieDetails(movieId) { videoUrl ->
            if (videoUrl != null) {
                tv.setText(videoUrl.toString())

                mostrarVideo(videoUrl)

//                val uri: Uri  = Uri.parse(videoUrl)
//                videoView.setVideoURI(uri)
//

            } else {
                println("El video no está disponible.")
            }
        }
        return view

    }

    private fun mostrarVideo(urlVideo: String) {
        val videoUri = Uri.parse(urlVideo)
        Log.d("Video", "URL del video: $videoUri")

        videoView.setVideoURI(videoUri)
        val mediaController = MediaController(requireContext())
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        videoView.setOnPreparedListener {
            Log.d("Video", "El video ha comenzado a cargar.")
            videoView.start()
        }

        videoView.setOnCompletionListener {
            Log.d("Video", "El video ha terminado de cargar.")
        }
    }

}
