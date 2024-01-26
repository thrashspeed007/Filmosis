package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiInterface {
    @GET("discover/movie?sort_by=popularity.desc&language=es-ES")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<RemoteResult>
}

