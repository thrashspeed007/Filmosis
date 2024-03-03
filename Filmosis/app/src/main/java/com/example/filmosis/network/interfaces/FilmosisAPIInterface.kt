package com.example.filmosis.network.interfaces

import com.example.filmosis.data.model.filmosis.FavouriteMovies
import retrofit2.Call
import retrofit2.http.GET

interface FilmosisAPIInterface {
    @GET("servicio-peliculas")
    fun getFavouriteMovies(): Call<Int>
}