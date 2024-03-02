package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.tmdb.MoviesPage
import com.example.filmosis.dataclass.MovieDetailsResponse
import com.example.filmosis.dataclass.PeliculaDetalles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiInterface {
    // Para montar querys personalizadas:
    // https://developer.themoviedb.org/reference/discover-movie

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<MoviesPage>

    @GET("discover/movie?language=es-ES")
    fun listUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<MoviesPage>

    @GET("movie/{movie_id}/recommendations?language=es-ES")
    fun getRecommendedMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MoviesPage>

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<MoviesPage>

    @GET("search/movie?language=es-ES&sort_by=popularity.desc")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String
    ): Call<MoviesPage>

    fun obtenerDetallesPelicula(
        @Path("movie_id") idPelicula: Int,
        @Query("api_key") apiKey: String
    ): Call<PeliculaDetalles>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "videos"
    ): Call<MovieDetailsResponse>

    @GET("discover/movie?language=es-ES&sort_by=vote_average.desc&vote_count.gte=50")
    fun listBestRatedMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<MoviesPage>

    @GET("discover/movie?language=es-ES&sort_by=release_date.desc&vote_count.gte=20")
    fun listLatestMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String,
        @Query("primary_release_date.lte") releaseDateLTE: String // Fecha de lanzamiento menor o igual que
    ): Call<MoviesPage>

    @GET("trending/movie/week?language=es-ES")
    fun listTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<MoviesPage>

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listUpcomingMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<MoviesPage>


}

