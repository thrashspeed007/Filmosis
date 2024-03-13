package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa a una persona (actor, director, etc.) según la API de TMDB.
 * @property adult Indica si la persona es mayor de edad.
 * @property gender El género de la persona (1 para mujeres, 2 para hombres).
 * @property id El ID único de la persona.
 * @property known_for La lista de producciones conocidas por la persona.
 * @property known_for_department El departamento conocido de la persona.
 * @property name El nombre de la persona.
 * @property original_name El nombre original de la persona.
 * @property popularity La popularidad de la persona.
 * @property profile_path La ruta de la imagen de perfil de la persona.
 */
data class Person(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)