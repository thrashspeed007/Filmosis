package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiInterface {
    // Para montar querys personalizadas:
    // https://developer.themoviedb.org/reference/discover-movie

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<RemoteResult>

    @GET("discover/movie?language=es-ES&sort_by=popularity.desc")
    fun listPopularMoviesWithGenres(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
        @Query("with_genres") genres: String
    ): Call<RemoteResult>

}

