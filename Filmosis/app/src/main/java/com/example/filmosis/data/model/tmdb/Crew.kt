package com.example.filmosis.data.model.tmdb


/**
 * Clase que representa un miembro del equipo de producción de una producción.
 * @property adult Indica si el miembro del equipo de producción es mayor de edad.
 * @property backdrop_path La ruta de fondo de la imagen del miembro del equipo de producción.
 * @property credit_id El ID del crédito asociado al miembro del equipo de producción.
 * @property department El departamento al que pertenece el miembro del equipo de producción.
 * @property episode_count El número de episodios en los que ha participado si el miembro del equipo de producción es parte de una serie de televisión.
 * @property first_air_date La fecha de estreno de la primera emisión si el miembro del equipo de producción es parte de una serie de televisión.
 * @property genre_ids La lista de IDs de género asociados al miembro del equipo de producción.
 * @property id El ID único del miembro del equipo de producción.
 * @property job El trabajo desempeñado por el miembro del equipo de producción.
 * @property media_type El tipo de medio al que pertenece el miembro del equipo de producción (película, serie de televisión, etc.).
 * @property name El nombre del miembro del equipo de producción.
 * @property origin_country La lista de países de origen del miembro del equipo de producción.
 * @property original_language El idioma original de la producción en la que participa el miembro del equipo de producción.
 * @property original_name El nombre original del miembro del equipo de producción.
 * @property overview Una descripción general de la producción en la que participa el miembro del equipo de producción.
 * @property popularity La popularidad del miembro del equipo de producción.
 * @property poster_path La ruta de la imagen del póster de la producción en la que participa el miembro del equipo de producción.
 * @property vote_average El promedio de votos de la producción en la que participa el miembro del equipo de producción.
 * @property vote_count El número total de votos recibidos por la producción en la que participa el miembro del equipo de producción.
 * @property profile_path La ruta de la imagen de perfil del miembro del equipo de producción.
 */
data class Crew(
    val adult: Boolean,
    val backdrop_path: String,
    val credit_id: String,
    val department: String,
    val episode_count: Int,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val job: String,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    val profile_path: String

)



/**
 * Clase que representa la respuesta obtenida al solicitar información sobre el equipo de producción de una producción.
 * @property crew La lista de miembros del equipo de producción de la producción.
 */
data class CreditsResponse(
    val crew: List<Crew>,

)
