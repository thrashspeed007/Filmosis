package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiInterface {
    @GET("discover/movie?sort_by=popularity.desc&language=es-ES")
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







}

