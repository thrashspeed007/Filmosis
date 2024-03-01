package com.example.filmosis.data.access.tmdb

import android.util.Log
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.RemoteResult
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.dataclass.MovieDetailsResponse
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoviesAccess {

    fun listPopularMovies(callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listPopularMovies onFailure: " + t.message )
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

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listPopularMoviesWithGenres onFailure: " + t.message )
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
        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listUpcomingMovies onFailure: " + t.message )
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

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listRecommendedMovies onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun searchMovie(query: String, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.searchMovie(DatosConexion.API_KEY, DatosConexion.REGION, query)

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "searchMovie onFailure: " + t.message)
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    //Para conseguir el link del video
    fun getMovieDetails(movieId: Int, callback: (String?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieDetails(movieId, DatosConexion.API_KEY, "videos")

        call.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                Log.d("MoviesAccess", "getMovieDetails onFailure: " + t.message)
                callback.invoke(null)
            }

            override fun onResponse(call: Call<MovieDetailsResponse>, response: Response<MovieDetailsResponse>) {
                val videoKey = response.body()?.videos?.results?.firstOrNull()?.key
                if (videoKey != null) {
                    val videoUrl = "https://www.youtube.com/watch?v=$videoKey"
                    callback.invoke(videoUrl)
                } else {
                    callback.invoke(null)
                }
            }
        })
    }

    fun listTrendingMovies(callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listTrendingMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listTrendingMovies onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listBestRatedMoviesWithGenres(genres: List<Int>, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listMoviesWithGenresBestRated(DatosConexion.API_KEY, DatosConexion.REGION, genres.joinToString(separator = "||"))

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listBestRatedMoviesWithGenres onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listLatestMoviesWithGenres(genres: List<Int>, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listMoviesWithGenresLatest(DatosConexion.API_KEY, DatosConexion.REGION, genres.joinToString(separator = "||"))

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listLatestMoviesWithGenres onFailure: " + t.message )
            }

            override fun onResponse(call: Call<RemoteResult>, response: Response<RemoteResult>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listUpcomingMoviesWithGenres(genres: List<Int>, callback: (List<Result>) -> Unit) {
        val call = RetrofitService.tmdbApi.listUpcomingMoviesWithGenres(DatosConexion.API_KEY, DatosConexion.REGION, genres.joinToString(separator = "||"), getCurrentDate())

        call.enqueue(object : Callback<RemoteResult> {
            override fun onFailure(call: Call<RemoteResult>, t: Throwable) {
                Log.d("MoviesAccess", "listUpcomingMoviesWithGenres onFailure: " + t.message )
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