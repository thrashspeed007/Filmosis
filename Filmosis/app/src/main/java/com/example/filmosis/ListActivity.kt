package com.example.filmosis
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filmosis.data.access.filmosis.FilmosisAccess
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class ListActivity : AppCompatActivity() {
    private lateinit var listResultTextView: TextView

    private val filmosisAccess : FilmosisAccess = FilmosisAccess();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listResultTextView = findViewById(R.id.list_result)

            Log.d("filmosis api","gajhshdga")
        filmosisAccess.listFavouriteMovies {
            Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_LONG).show()
            listResultTextView.text = it.toString()
        }
    }
}
