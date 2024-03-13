package com.example.filmosis.network.interfaces


import com.example.filmosis.data.model.tmdb.CombinedCredits
import com.example.filmosis.data.model.tmdb.CreditsCast
import com.example.filmosis.data.model.tmdb.CreditsResponse
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.Moviefr
import com.example.filmosis.data.model.tmdb.MoviesPage
import com.example.filmosis.data.model.tmdb.PersonDetails
import com.example.filmosis.data.model.tmdb.PersonsPage
import com.example.filmosis.dataclass.MovieDetailsResponse
import com.example.filmosis.dataclass.Network
import com.example.filmosis.dataclass.NetworkDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define los puntos finales (endpoints) de la API de TMDb (The Movie Database) para realizar operaciones relacionadas con películas y personas.
 */
interface TmdbApiInterface {
    // Para montar querys personalizadas:
    // https://developer.themoviedb.org/reference/discover-movie

    /**
     * Obtiene una lista de las películas populares.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las películas populares.
     */
    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las próximas películas.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param releaseDateGTE Fecha de lanzamiento mayor o igual que.
     * @return [Call] que devuelve una lista de [MoviesPage] que representan las próximas películas.
     */
    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las próximas recomendadas.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param movieId El id de la película sobre la que se recomendar películas similares
     * @return [Call] que devuelve una lista de [MoviesPage] que representan las próximas películas.
     */
    @GET("movie/{movie_id}/recommendations?language=es-ES")
    fun getRecommendedMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las películas populares con géneros específicos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las películas populares filtradas por género.
     */
    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<MoviesPage>

    /**
     * Busca películas por un término de búsqueda.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param query Término de búsqueda.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las películas que coinciden con el término de búsqueda.
     */
    @GET("search/movie?language=es-ES&sort_by=popularity.desc")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String
    ): Call<MoviesPage>

    /**
     * Obtiene los detalles de una película específica.
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @param appendToResponse Parámetro opcional para especificar datos adicionales que se desean incluir en la respuesta, por defecto se incluyen los vídeos.
     * @return [Call] que devuelve una respuesta de [MovieDetailsResponse] que contiene los detalles de la película.
     */
    @GET("movie/{movie_id}?language=es-ES")
    fun getMovieVideo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "videos"
    ): Call<MovieDetailsResponse>

    /**
     * Obtiene una lista de películas mejor valoradas con géneros específicos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las películas mejor valoradas filtradas por género.
     */
    @GET("discover/movie?language=es-ES&sort_by=vote_average.desc&vote_count.gte=50")
    fun listBestRatedMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las mejores películas con un mínimo de votos y excluyendo ciertos géneros.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las mejores películas según la calificación de los usuarios y otros criterios de filtrado.
     */
    @GET("discover/movie?include_adult=false&include_video=false&language=es-ES&page=1&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200")
    fun listBestRatedMoves(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las últimas películas con géneros específicos y un mínimo de votos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @param releaseDateLTE Fecha de lanzamiento menor o igual que.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las últimas películas con los géneros especificados y un mínimo de votos.
     */
    @GET("discover/movie?language=es-ES&sort_by=release_date.desc&vote_count.gte=20")
    fun listLatestMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String,
        @Query("primary_release_date.lte") releaseDateLTE: String // Fecha de lanzamiento menor o igual que
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las últimas películas con géneros específicos y un mínimo de votos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @param releaseDateLTE Fecha de lanzamiento menor o igual que.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las últimas películas con los géneros especificados y un mínimo de votos.
     */
    @GET("discover/movie?language=es-ES&sort_by=release_date.desc&vote_count.gte=20")
    fun listLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("primary_release_date.lte") releaseDateLTE: String // Fecha de lanzamiento menor o igual que
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las últimas películas con géneros específicos y un mínimo de votos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @param releaseDateLTE Fecha de lanzamiento menor o igual que.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las últimas películas con los géneros especificados y un mínimo de votos.
     */
    @GET("trending/movie/week?language=es-ES")
    fun listTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<MoviesPage>

    /**
     * Obtiene una lista de las próximas películas con géneros específicos.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las películas.
     * @param genres Géneros de las películas a filtrar.
     * @param releaseDateGTE Fecha de lanzamiento mayor o igual que.
     * @return [Call] que devuelve una respuesta de [MoviesPage] que contiene las próximas películas con los géneros especificados y un mínimo de votos.
     */
    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listUpcomingMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<MoviesPage>

    /**
     * Obtiene los créditos de una película específica.
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [CreditsResponse] que contiene los créditos de la película.
     */
    @GET("movie/{movie_id}/credits?language=es-ES")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<CreditsResponse>

    /**
     * Obtiene los créditos de una película específica (Versión alternativa para los actores).
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [CreditsCast] que contiene los créditos de la película.
     */
    @GET("movie/{movie_id}/credits?language=es-ES")
    fun getMovieCredits2(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<CreditsCast>

    /**
     * Obtiene los créditos combinados de una persona específica.
     *
     * @param personId ID de la persona.
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener los créditos.
     * @return [Call] que devuelve una respuesta de [CombinedCredits] que contiene los créditos combinados de la persona.
     */
    @GET("person/{person_id}/combined_credits?language=es-ES")
    fun getPersonCombinedCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ) : Call<CombinedCredits>

    /**
     * Busca personas por un término de búsqueda.
     *
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener las personas.
     * @param query Término de búsqueda.
     * @return [Call] que devuelve una respuesta de [PersonsPage] que contiene las personas que coinciden con el término de búsqueda.
     */
    @GET("search/person?language=es-ES&page=1")
    fun searchPerson(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String
    ): Call<PersonsPage>

    /**
     * Obtiene los detalles de una persona específica.
     *
     * @param personId ID de la persona.
     * @param apiKey Clave de la API de TMDb.
     * @param region Región para la que se desean obtener los detalles de la persona.
     * @return [Call] que devuelve una respuesta de [PersonDetails] que contiene los detalles de la persona.
     */
    @GET("person/{person_id}?language=es-ES")
    fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<PersonDetails>


    //TODO cambiar el netork con https://api.themoviedb.org/3/movie/{movie_id}/watch/providers
    //https://developer.themoviedb.org/reference/movie-watch-providers

    /**
     * Obtiene los detalles de una red específica.
     *
     * @param networkId ID de la red.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [Network] que contiene los detalles de la red.
     */
    @GET("network/{id}")
    fun getNetworkDetails(
        @Path("id") networkId: Int,
        @Query("api_key") apiKey: String
    ): Call<Network>

    /**
     * Obtiene los proveedores de transmisión para una película específica.
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [NetworkDetailsResponse] que contiene los proveedores de transmisión para la película.
     */
    @GET("movie/{movie_id}/watch/providers")
    fun getStreamingProviders(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<NetworkDetailsResponse>

    /**
     * Obtiene los detalles de una película específica con los géneros recuperados.
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [Moviefr] que contiene los detalles de la película con los géneros recuperados.
     */
    @GET("movie/{movie_id}?language=es-ES")
    fun getMovieDetailsRecuperarGenres(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Moviefr>

    /**
     * Obtiene los detalles de una película específica.
     *
     * @param movieId ID de la película.
     * @param apiKey Clave de la API de TMDb.
     * @return [Call] que devuelve una respuesta de [Movie] que contiene los detalles de la película.
     */
    @GET("movie/{movie_id}?language=es-ES")
    fun getMovieDetailsRecuperar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>
}


