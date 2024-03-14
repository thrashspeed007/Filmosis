package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa un miembro del equipo de producción de una producción según la API de TMDB.
 * @property adult Indica si el miembro del equipo de producción es mayor de edad.
 * @property credit_id El ID del crédito asociado al miembro del equipo de producción.
 * @property department El departamento al que pertenece el miembro del equipo de producción.
 * @property gender El género del miembro del equipo de producción (1 para mujeres, 2 para hombres).
 * @property id El ID único del miembro del equipo de producción.
 * @property job El trabajo desempeñado por el miembro del equipo de producción.
 * @property known_for_department El departamento conocido del miembro del equipo de producción.
 * @property name El nombre del miembro del equipo de producción.
 * @property original_name El nombre original del miembro del equipo de producción.
 * @property popularity La popularidad del miembro del equipo de producción.
 * @property profile_path La ruta de la imagen de perfil del miembro del equipo de producción.
 */
data class CrewX(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)