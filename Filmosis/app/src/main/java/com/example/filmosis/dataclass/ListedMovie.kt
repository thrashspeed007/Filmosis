package com.example.filmosis.dataclass
/**
 * Clase de datos que representa una película listada en una lista.
 * @param title Título de la película.
 * @param poster_path Ruta del póster de la película.
 * @param releaseDate Fecha de lanzamiento de la película.
 * @param averageVote Voto promedio de la película.
 * @param id Identificador único de la película.
 */

data class ListedMovie(
    val title: String,
    val poster_path: String,
    val releaseDate: String,
    val averageVote: Double,
    val id: Int
)
