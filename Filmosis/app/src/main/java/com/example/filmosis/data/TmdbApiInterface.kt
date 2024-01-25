package com.example.filmosis.data

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.RemoteResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiInterface {
    @GET("discover/movie?sort_by=popularity.desc&language=es-ES")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Call<RemoteResult>
}

object TmdbRetrofitService {
    fun makeRetrofitService(): TmdbApiInterface {
        return Retrofit.Builder()
            .baseUrl(DatosConexion.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TmdbApiInterface::class.java)
    }
}