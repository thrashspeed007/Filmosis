package com.example.filmosis.data.access.filmosis

import android.util.Log
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.filmosis.FavouriteMovies
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.MoviesPage
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmosisAccess {
//    fun listPopularMovies(callback: (List<Movie>) -> Unit) {
//        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)
//
//        call.enqueue(object : Callback<MoviesPage> {
//            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
//                Log.d("MoviesAccess", "listPopularMovies onFailure: " + t.message )
//            }
//
//            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
//                val movies = response.body()?.results
//
//                if (movies != null) {
//                    callback.invoke(movies)
//                }
//            }
//        })
//    }

    fun listFavouriteMovies(callback: (Int) -> Unit) {
        val call = RetrofitService.filmosisApi.getFavouriteMovies()

        call.enqueue(object : Callback<Int>{
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("Filmosis access",t.message.toString())
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val favouriteMoviesIds : Int? = response.body()
                if (favouriteMoviesIds != null) {
                    callback.invoke(favouriteMoviesIds)
                }
            }
        })
    }
}