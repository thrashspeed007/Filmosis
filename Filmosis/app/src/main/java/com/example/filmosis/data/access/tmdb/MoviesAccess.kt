package com.example.filmosis.data.access.tmdb

import android.util.Log
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.CastX
import com.example.filmosis.data.model.tmdb.CreditsCast
import com.example.filmosis.data.model.tmdb.CreditsResponse
import com.example.filmosis.data.model.tmdb.Crew

import com.example.filmosis.data.model.tmdb.MoviesPage
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.Moviefr
import com.example.filmosis.dataclass.MovieDetailsResponse
import com.example.filmosis.dataclass.Network
import com.example.filmosis.dataclass.NetworkDetailsResponse
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Clase que proporciona métodos para acceder a datos relacionados con películas desde la API de TMDb.
 * Se utiliza la interfaz TmdbApiInterface donde se declaran las consultas con los parámetros necesarios
 *
 * @constructor Crea un objeto MoviesAccess.
 */
class MoviesAccess {

    /**
     * Obtiene una lista de películas populares.
     *
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun listPopularMovies(callback: (List<Movie>) -> Unit) {
        val call =
            RetrofitService.tmdbApi.listPopularMovies(DatosConexion.API_KEY, DatosConexion.REGION)

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

    /**
     * Obtiene una lista de películas populares con los géneros especificados.
     *
     * @param genres Lista de identificadores de géneros.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene una lista de películas próximas.
     *
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene una lista de películas recomendadas para una película específica.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Busca películas basadas en una consulta proporcionada.
     *
     * @param query Consulta de búsqueda.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene el enlace al video de YouTube de una película si está disponible.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getMovieVideo(movieId: Int, callback: (String?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieVideo(movieId, DatosConexion.API_KEY, "videos")

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

    /**
     * Obtiene una lista de películas en tendencia.
     *
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene una lista de películas mejor valoradas con los géneros especificados.
     *
     * @param genres Lista de identificadores de géneros.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene una lista de películas mejor valoradas.
     *
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun listBestRatedMovies(callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listBestRatedMoves(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listBestRatedMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    /**
     * Obtiene una lista de películas más recientes con los géneros especificados.
     *
     * @param genres Lista de identificadores de géneros.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene una lista de películas más recientes.
     *
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun listLatestMovies(callback: (List<Movie>) -> Unit) {
        val call = RetrofitService.tmdbApi.listLatestMovies(
            DatosConexion.API_KEY,
            DatosConexion.REGION,
            getCurrentDate()
        )

        call.enqueue(object : Callback<MoviesPage> {
            override fun onFailure(call: Call<MoviesPage>, t: Throwable) {
                Log.d("MoviesAccess", "listLatestMovies onFailure: " + t.message)
            }

            override fun onResponse(call: Call<MoviesPage>, response: Response<MoviesPage>) {
                val movies = response.body()?.results

                if (movies != null) {
                    callback.invoke(movies)
                }
            }
        })
    }

    /**
     * Obtiene una lista de próximas películas con los géneros especificados.
     *
     * @param genres Lista de identificadores de géneros.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
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

    /**
     * Obtiene los detalles de una película incluyendo información de géneros.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getMovieDataGenres(movieId: Int, callback: (Moviefr?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieDetailsRecuperarGenres(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<Moviefr> {
            override fun onFailure(call: Call<Moviefr>, t: Throwable) {
                Log.d("MoviesAccess", "getMovieData onFailure: " + t.message)
                callback.invoke(null)
            }

            override fun onResponse(call: Call<Moviefr>, response: Response<Moviefr>) {
                val movie = response.body()
                callback.invoke(movie)
            }
        })
    }

    /**
     * Obtiene los detalles de una película.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getMovieData(movieId: Int, callback: (Movie?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieDetailsRecuperar(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("MoviesAccess", "getMovieData onFailure: " + t.message)
                callback.invoke(null)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val movie = response.body()
                callback.invoke(movie)
            }
        })
    }

    /**
     * Obtiene los directores de una película.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getMovieDirectors(movieId: Int, callback: (List<Crew>?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieCredits(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(
                call: Call<CreditsResponse>,
                response: Response<CreditsResponse>
            ) {
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

    /**
     * Obtiene los actores de una película.
     *
     * @param movieId Identificador de la película.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getMovieActors(movieId: Int, callback: (List<CastX>?) -> Unit) {
        val call = RetrofitService.tmdbApi.getMovieCredits2(movieId, DatosConexion.API_KEY)
        call.enqueue(object : Callback<CreditsCast> {
            override fun onResponse(
                call: Call<CreditsCast>,
                response: Response<CreditsCast>
            ) {
                if (response.isSuccessful) {
                    val creditsResponse = response.body()
                    if (creditsResponse != null) {
                        val actorList = creditsResponse.cast.filter { it.known_for_department == "Acting" }
                        callback(actorList)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<CreditsCast>, t: Throwable) {
                callback(null)
            }
        })
    }

    /**
     * Obtiene los detalles de una red de transmisión.
     *
     * @param networkId Identificador de la red de transmisión.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun fetchNetworkDetails(networkId: Int, callback: (Network?) -> Unit) {
        val call = RetrofitService.tmdbApi.getNetworkDetails(networkId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<Network> {
            override fun onResponse(call: Call<Network>, response: Response<Network>) {
                if (response.isSuccessful) {
                    val networkDetailsResponse = response.body()
                    callback(networkDetailsResponse)
                } else {
                    println("Solicitud fallida - Código de estado: ${response.code()}")
                    callback(null) // Handle failure by invoking the callback with null
                }
            }

            override fun onFailure(call: Call<Network>, t: Throwable) {
                println("Error de red: ${t.message}")
                callback(null) // Handle failure by invoking the callback with null
            }
        })
    }

    /**
     * Obtiene proovedores de streaming de una película
     *
     * @param networkId Identificador de la red de transmisión.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun fetchNetworkDetails2(movieId: Int, callback: (NetworkDetailsResponse?) -> Unit) {
        val call = RetrofitService.tmdbApi.getStreamingProviders(movieId, DatosConexion.API_KEY)

        call.enqueue(object : Callback<NetworkDetailsResponse> {
            override fun onResponse(
                call: Call<NetworkDetailsResponse>,
                response: Response<NetworkDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val networkDetailsResponse = response.body()
                    callback(networkDetailsResponse)
                } else {
                    println("Solicitud fallida - Código de estado: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NetworkDetailsResponse>, t: Throwable) {
                println("Error de red: ${t.message}")
            }
        })
    }

    /**
     * Obtiene la fecha actual en el formato "yyyy-MM-dd".
     *
     * @return La fecha actual en el formato especificado.
     */
    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }

}