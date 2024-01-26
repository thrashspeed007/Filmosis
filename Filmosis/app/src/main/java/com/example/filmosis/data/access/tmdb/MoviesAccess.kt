package com.example.filmosis.data.access.tmdb

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.RemoteResult
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MoviesAccess {

    fun listPopularMovies(callback: (List<String>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)
        val moviesList: ArrayList<String> = ArrayList()

        call.enqueue(object : retrofit2.Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    for (movie in movies) {
                        moviesList.add(movie.title + ": " + movie.vote_average)
                    }
                }

                callback.invoke(moviesList)
            }
        })
    }
}