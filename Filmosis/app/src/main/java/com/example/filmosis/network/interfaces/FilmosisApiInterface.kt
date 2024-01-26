package com.example.filmosis.network.interfaces

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