package com.example.filmosis.data.model.filmosis

    data class FavouriteMovies(
        val titulo : String,
        val director : String,
        val generos : List<String>,
        val fechaEstreno : String
    )
