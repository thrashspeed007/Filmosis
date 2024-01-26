package com.example.filmosis

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.data.access.filmosis.UsersAccess
import com.example.filmosis.data.access.tmdb.MoviesAccess

class MainActivity : AppCompatActivity() {

    private val usersAccess = UsersAccess()
    private val moviesAccess = MoviesAccess()

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        moviesAccess.listPopularMovies { result ->
            textView.text = result.joinToString("\n")
        }

//        usersAccess.getUsers { result ->
//            textView.text = result.joinToString("\n")
//        }
    }
}