package com.example.filmosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.network.RetrofitService
import com.example.filmosis.data.model.filmosis.UsuarioItem
import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

//        getUsuarios()

        listPopularMovies()
    }

    private fun listPopularMovies() {
        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : retrofit2.Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results
                var s = ""

                if (movies != null) {
                    for (movie in movies) {
                        s += movie.title + ": " + movie.vote_average + "\n"
                    }
                }

                textView.text = s
            }
        })
    }

    private fun getUsuarios() {
        val call = RetrofitService.filmosisApi.getUsuarios()

        call.enqueue(object : retrofit2.Callback<List<UsuarioItem>>{
            override fun onFailure(call: Call<List<UsuarioItem>>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }

            override fun onResponse(
                call: Call<List<UsuarioItem>>,
                response: Response<List<UsuarioItem>>
            ) {
                val responseBody = response.body()

                val sb = StringBuilder()
                if (responseBody != null) {
                    for (usuario in responseBody) {
                        sb.append(usuario.username + ": " + usuario.nombre + " " + usuario.apellidos + "\n")
                    }
                } else {
                    android.util.Log.d("MainActivity", "onFailure: responseBody is null" )
                }

                textView.text = sb.toString()
            }
        })
    }
}