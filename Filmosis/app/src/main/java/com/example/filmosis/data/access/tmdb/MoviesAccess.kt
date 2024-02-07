package com.example.filmosis.data.access.tmdb

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.RemoteResult
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MoviesAccess {

    fun listPopularMovies(callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : retrofit2.Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }
}