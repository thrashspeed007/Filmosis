package com.example.filmosis.data.model.tmdb


/**
 * Clase que representa una película según la API de TMDB.
 * @property adult Indica si la película es para adultos.
 * @property backdrop_path La ruta de fondo de la imagen de la película.
 * @property genre_ids La lista de IDs de género asociados a la película.
 * @property id El ID único de la película.
 * @property original_language El idioma original de la película.
 * @property original_title El título original de la película.
 * @property overview Una descripción general de la película.
 * @property popularity La popularidad de la película.
 * @property poster_path La ruta de la imagen del póster de la película.
 * @property release_date La fecha de estreno de la película.
 * @property title El título de la película.
 * @property video Indica si la película tiene video.
 * @property vote_average El promedio de votos de la película.
 * @property vote_count El número total de votos recibidos por la película.
 * @property spoken_languages La lista de idiomas hablados en la película.
 */
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
    val spoken_languages : List<Idioma>
)

//https://developer.themoviedb.org/reference/movie-details
/**
 * Clase que representa una película según la API de TMDB (francés).
 * @property adult Indica si la película es para adultos.
 * @property backdrop_path La ruta de fondo de la imagen de la película.
 * @property genres La lista de géneros asociados a la película.
 * @property id El ID único de la película.
 * @property original_language El idioma original de la película.
 * @property original_title El título original de la película.
 * @property overview Una descripción general de la película.
 * @property popularity La popularidad de la película.
 * @property poster_path La ruta de la imagen del póster de la película.
 * @property release_date La fecha de estreno de la película.
 * @property title El título de la película.
 * @property video Indica si la película tiene video.
 * @property vote_average El promedio de votos de la película.
 * @property vote_count El número total de votos recibidos por la película.
 * @property spoken_languages La lista de idiomas hablados en la película.
 */

data class Moviefr(
    val adult: Boolean,
    val backdrop_path: String,
    val genres: List<Genre>,
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
    val spoken_languages : List<Idioma>
)

/**
 * Clase que representa un idioma según la API de TMDB.
 * @property english_name El nombre en inglés del idioma.
 * @property iso_639_1 El código ISO 639-1 del idioma.
 * @property name El nombre del idioma.
 */
data class Idioma(val english_name:String, val iso_639_1 : String, val name : String )

/**
 * Clase que representa un género según la API de TMDB.
 * @property id El ID único del género.
 * @property name El nombre del género.
 */
data class Genre(
    val id: Int,
    val name: String
)






