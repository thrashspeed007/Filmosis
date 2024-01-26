package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.filmosis.UsuarioItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface FilmosisApiInterface {

    @GET("obtenerUsuarios.php")
    fun getUsuarios(): Call<List<UsuarioItem>>

    @POST("autenticarUsuario.php")
    fun autenticar(): Call<Boolean>
}