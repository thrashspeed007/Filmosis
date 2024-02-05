package com.example.filmosis

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.data.access.tmdb.MoviesAccess

class MainActivity : AppCompatActivity() {
    private val moviesAccess = MoviesAccess()

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        moviesAccess.listPopularMovies { result ->
            textView.text = result.joinToString("\n")
        }

        // keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
        //         SHA1: E1:F3:F6:81:0F:66:6C:A1:1D:77:1F:5B:31:6E:D5:E4:BA:70:E4:C7
        //         SHA256: 22:2C:14:61:50:40:48:54:85:6E:6E:F5:09:FD:9F:A6:24:C3:02:FA:7F:27:A3:9D:A3:09:40:33:18:C4:5A:47
    }
}