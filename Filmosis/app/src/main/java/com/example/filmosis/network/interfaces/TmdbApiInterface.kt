package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.tmdb.RemoteResult
import com.example.filmosis.dataclass.MovieDetailsResponse
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
    ): Call<RemoteResult>

    @GET("discover/movie?language=es-ES")
    fun listUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<RemoteResult>

    @GET("movie/{movie_id}/recommendations?language=es-ES")
    fun getRecommendedMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<RemoteResult>

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<RemoteResult>

    @GET("search/movie?language=es-ES&sort_by=popularity.desc")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("query") query: String
    ): Call<RemoteResult>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "videos"
    ): Call<MovieDetailsResponse>

    @GET("discover/movie?language=es-ES&sort_by=vote_average.desc&vote_count.gte=50")
    fun listMoviesWithGenresBestRated(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<RemoteResult>

    @GET("discover/movie?language=es-ES&sort_by=release_date.desc")
    fun listMoviesWithGenresLatest(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<RemoteResult>

    @GET("trending/movie/week?language=es-ES")
    fun listTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<RemoteResult>

    @GET("discover/movie?language=es-ES")
    fun listUpcomingMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String,
        @Query("primary_release_date.gte") releaseDateGTE: String // Fecha de lanzamiento mayor o igual que
    ): Call<RemoteResult>

}

