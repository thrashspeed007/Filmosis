package com.example.filmosis.data.access.tmdb

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.RemoteResult
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    fun listPopularMoviesWithGenres(genres: List<Int>, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMoviesWithGenres(DatosConexion.API_KEY, DatosConexion.REGION, genres.joinToString(separator = "||"))

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

    fun listUpcomingMovies(callback: (List<Result>) -> Unit) {
        val currentDate = getCurrentDate()
        val call = RetrofitService.tmdbApi.listUpcomingMovies(DatosConexion.API_KEY, DatosConexion.REGION,currentDate )
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

    fun listRecommendedMovies(movieId: Int, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.getRecommendedMovies(movieId, DatosConexion.API_KEY)

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



    //Lo necesito para hacer la consulta a la bd
    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }






}