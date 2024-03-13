package com.example.filmosis.data.model.tmdb


/**
 * Clase que representa los detalles de una persona (actor, director, etc.) según la API de TMDB.
 * @property adult Indica si la persona es mayor de edad.
 * @property also_known_as Una lista de otros nombres conocidos de la persona.
 * @property biography La biografía de la persona.
 * @property birthday La fecha de nacimiento de la persona.
 * @property deathday La fecha de fallecimiento de la persona (puede ser nulo si la persona está viva).
 * @property gender El género de la persona (1 para mujeres, 2 para hombres).
 * @property homepage La página web personal de la persona (puede ser nulo).
 * @property id El ID único de la persona.
 * @property imdb_id El ID de IMDb de la persona.
 * @property known_for_department El departamento conocido de la persona.
 * @property name El nombre de la persona.
 * @property place_of_birth El lugar de nacimiento de la persona.
 * @property popularity La popularidad de la persona.
 * @property profile_path La ruta de la imagen de perfil de la persona.
 */
data class PersonDetails(
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: Any,
    val gender: Int,
    val homepage: Any,
    val id: Int,
    val imdb_id: String,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String,
    val popularity: Double,
    val profile_path: String
)