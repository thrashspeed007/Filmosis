package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa un miembro del elenco de una producción.
 * @property adult Indica si el miembro del elenco es mayor de edad.
 * @property backdrop_path La ruta de fondo de la imagen del miembro del elenco.
 * @property character El personaje interpretado por el miembro del elenco.
 * @property credit_id El ID del crédito asociado al miembro del elenco.
 * @property episode_count El número de episodios en los que ha participado si el miembro del elenco es parte de una serie de televisión.
 * @property first_air_date La fecha de estreno de la primera emisión si el miembro del elenco es parte de una serie de televisión.
 * @property genre_ids La lista de IDs de género asociados al miembro del elenco.
 * @property id El ID único del miembro del elenco.
 * @property media_type El tipo de medio al que pertenece el miembro del elenco (película, serie de televisión, etc.).
 * @property name El nombre del miembro del elenco.
 * @property order El orden de importancia del miembro del elenco en la producción.
 * @property origin_country La lista de países de origen del miembro del elenco.
 * @property original_language El idioma original de la producción en la que participa el miembro del elenco.
 * @property original_name El nombre original del miembro del elenco.
 * @property original_title El título original de la producción en la que participa el miembro del elenco.
 * @property overview Una descripción general de la producción en la que participa el miembro del elenco.
 * @property popularity La popularidad del miembro del elenco.
 * @property poster_path La ruta de la imagen del póster de la producción en la que participa el miembro del elenco.
 * @property release_date La fecha de estreno de la producción en la que participa el miembro del elenco.
 * @property title El título de la producción en la que participa el miembro del elenco.
 * @property video Indica si la producción en la que participa el miembro del elenco tiene video.
 * @property vote_average El promedio de votos de la producción en la que participa el miembro del elenco.
 * @property vote_count El número total de votos recibidos por la producción en la que participa el miembro del elenco.
 * @property known_for_department El departamento conocido del miembro del elenco.
 * @property profile_path La ruta de la imagen de perfil del miembro del elenco.
 */

data class Cast(
    val adult: Boolean,
    val backdrop_path: String,
    val character: String,
    val credit_id: String,
    val episode_count: Int,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val order: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val known_for_department: String,
    val profile_path : String
)

