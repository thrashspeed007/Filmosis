package com.example.filmosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.FilmosisApiInterface
import com.example.filmosis.data.TmdbApiInterface
import com.example.filmosis.data.UsuarioItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUsuarios()
    }

    private fun listPopularMovies() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(DatosConexion.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiInterface::class.java)
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