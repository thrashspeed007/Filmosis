package com.example.filmosis.data

import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.filmosis.Usuario
import com.example.filmosis.data.model.filmosis.UsuarioItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FilmosisApiInterface {

    @GET("obtenerUsuarios.php")
    fun getUsuarios(): Call<List<UsuarioItem>>
}

object FilmosisRetrofitServie {
    fun makeRetrofitService(): FilmosisApiInterface {
        return Retrofit.Builder()
            .baseUrl(DatosConexion.FILMOSIS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmosisApiInterface::class.java)
    }
}