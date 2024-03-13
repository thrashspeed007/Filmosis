package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa las producciones conocidas por un miembro del elenco o equipo de producción según la API de TMDB.
 * @property adult Indica si la producción es para adultos.
 * @property backdrop_path La ruta de fondo de la imagen de la producción.
 * @property genre_ids La lista de IDs de género asociados a la producción.
 * @property id El ID único de la producción.
 * @property media_type El tipo de medio de la producción (película, serie de televisión, etc.).
 * @property original_language El idioma original de la producción.
 * @property original_title El título original de la producción.
 * @property overview Una descripción general de la producción.
 * @property popularity La popularidad de la producción.
 * @property poster_path La ruta de la imagen del póster de la producción.
 * @property release_date La fecha de estreno de la producción.
 * @property title El título de la producción.
 * @property video Indica si la producción tiene video.
 * @property vote_average El promedio de votos de la producción.
 * @property vote_count El número total de votos recibidos por la producción.
 */
data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)