package com.example.filmosis.data.model.tmdb

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)


data class Director(
    val name: String,
    val profile_image_url: Int
)

data class Actor(
    val name: String,
    val profile_image_url: String
)

//Para recuperar los datos de la pelicula al seleccionarlo
data class MovieData(
    val movieId: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val posterPath: String,
)
