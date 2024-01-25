package com.example.filmosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.FilmosisApiInterface
import com.example.filmosis.data.FilmosisRetrofitServie
import com.example.filmosis.data.TmdbApiInterface
import com.example.filmosis.data.TmdbRetrofitService
import com.example.filmosis.data.model.filmosis.UsuarioItem
import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        val service = TmdbRetrofitService.makeRetrofitService()
        val call = service.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

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
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DatosConexion.FILMOSIS_BASE_URL)
            .build()
            .create(FilmosisApiInterface::class.java)

        val retrofitData = retrofitBuilder.getUsuarios()
        val textView : TextView = findViewById(R.id.textView)

        retrofitData.enqueue(object : retrofit2.Callback<List<UsuarioItem>?> {
            override fun onResponse(
                call: retrofit2.Call<List<UsuarioItem>?>,
                response: retrofit2.Response<List<UsuarioItem>?>
            ) {
                val responseBody = response.body()

                if (responseBody != null) {
                    val sb = java.lang.StringBuilder()
                    for (usuario in responseBody) {
                        sb.append(usuario.username + ": " + usuario.nombre + " " + usuario.apellidos + "\n")
                    }

                    textView.text = sb.toString()
                } else {
                    android.util.Log.d("MainActivity", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<UsuarioItem>?>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }
        })
    }
}