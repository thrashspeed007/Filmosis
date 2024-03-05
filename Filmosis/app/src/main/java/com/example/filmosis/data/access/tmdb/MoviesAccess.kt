package com.example.filmosis.data.access.tmdb

import android.util.Log
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.CreditsResponse
import com.example.filmosis.data.model.tmdb.Crew

import com.example.filmosis.data.model.tmdb.Director
import com.example.filmosis.data.model.tmdb.MoviesPage
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.Person
import com.example.filmosis.dataclass.MovieDetailsResponse
import com.example.filmosis.dataclass.PlatformDetails
import com.example.filmosis.dataclass.Servicio
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoviesAccess {

    fun listPopularMovies(callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listPopularMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listPopularMoviesWithGenres(genres: List<Int>, callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listPopularMoviesWithGenres(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            genres.joinToString(separator = "||")
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listPopularMoviesWithGenres onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listUpcomingMovies(callback: (List<Movie>) -> Unit) {
        val currentDate = getCurrentDate()
        val call = RetrofitService.tmdbApi.listUpcomingMovies(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            currentDate
        )
        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listUpcomingMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listRecommendedMovies(movieId: Int, callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.getRecommendedMovies(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listRecommendedMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun searchMovie(query: String, callback: (List<Movie>) -> Unit) {
        val call =
            RetrofitService.tmdbApi.searchMovie(DatosConexion.API_KEY, DatosConexion.REGION, query)

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "searchMovie onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
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

            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                val videoKey = response.body()?.videos?.results?.firstOrNull()?.key
                if (videoKey != null) {
                    val videoUrl = "https://www.youtube.com/embed/$videoKey"
                    callback.invoke(videoUrl)
                } else {
                    callback.invoke(null)
                }
            }
        })
    }


    fun listTrendingMovies(callback: (List<Movie>) -> Unit) {
        val call =
            RetrofitService.tmdbApi.listTrendingMovies(DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listTrendingMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listBestRatedMoviesWithGenres(genres: List<Int>, callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listBestRatedMoviesWithGenres(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            genres.joinToString(separator = "||")
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listBestRatedMoviesWithGenres onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listLatestMoviesWithGenres(genres: List<Int>, callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listLatestMoviesWithGenres(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            genres.joinToString(separator = "||"),
            getCurrentDate()
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listLatestMoviesWithGenres onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    fun listUpcomingMoviesWithGenres(genres: List<Int>, callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listUpcomingMoviesWithGenres(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            genres.joinToString(separator = "||"),
            getCurrentDate()
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listUpcomingMoviesWithGenres onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

//    fun getDirectorDetails(directorId: Int, callback: (MovieCredits?) -> Unit) {
//        val call = RetrofitService.tmdbApi.getDirectorDetails(directorId)
//
//        call.enqueue(object : Callback<MovieCredits> {
//            override fun onFailure(call: Call<MovieCredits>, t: Throwable) {
//                Log.d("MoviesAccess", "getDirectorDetails onFailure: " + t.message)
//                callback.invoke(null)
//            }
//
//            override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
//                val movieCredits = response.body()
//                callback.invoke(movieCredits)
//            }
//        })
//    }


    fun getDirectorDetails(movieId: Int, callback: (List<Crew>?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieCredits(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(call: Call<CreditsResponse>, response: Response<CreditsResponse>) {
                if (response.isSuccessful) {
                    val creditsResponse = response.body()
                    if (creditsResponse != null) {
                        val directorsList = creditsResponse.crew.filter { it.job == "Director" }
                        callback(directorsList)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getActorDetails(movieId: Int, callback: (List<Crew>?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieCredits(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(call: Call<CreditsResponse>, response: Response<CreditsResponse>) {
                if (response.isSuccessful) {
                    val creditsResponse = response.body()
                    if (creditsResponse != null) {
                        val actorList = creditsResponse.crew.filter { it.job == "Actor"}
                        callback(actorList)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
    fun getPlatformDetails(platformId: Int, callback: (PlatformDetails?) -> Unit) {
        val call = RetrofitService.tmdbApi.getPlatformDetails(platformId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<PlatformDetails> {
            override fun onResponse(call: Call<PlatformDetails>, response: Response<PlatformDetails>) {
                if (response.isSuccessful) {
                    val platformDetails = response.body()
                    callback(platformDetails)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PlatformDetails>, t: Throwable) {
                callback(null)
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