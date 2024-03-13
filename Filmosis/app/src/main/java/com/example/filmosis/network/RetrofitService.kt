package com.example.filmosis.network

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.network.interfaces.TmdbApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase singleton que proporciona un servicio Retrofit para interactuar con la API de TMDb (The Movie Database).
 */
object RetrofitService {

    /**
     * Objeto Retrofit para realizar llamadas a la API de TMDb.
     */
    private val tmdbRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(DatosConexion.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Interfaz que define los puntos finales (endpoints) de la API de TMDb para realizar operaciones relacionadas con películas y personas.
     */
    val tmdbApi: TmdbApiInterface by lazy {
        tmdbRetrofit.create(TmdbApiInterface::class.java)
    }
}
