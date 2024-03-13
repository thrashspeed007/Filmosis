package com.example.filmosis.data.model.tmdb


/**
 * Clase que representa un miembro del elenco de una producción según la API de TMDB.
 * @property adult Indica si el miembro del elenco es mayor de edad.
 * @property cast_id El ID de casting del miembro del elenco.
 * @property character El personaje interpretado por el miembro del elenco.
 * @property credit_id El ID del crédito asociado al miembro del elenco.
 * @property gender El género del miembro del elenco (1 para mujeres, 2 para hombres).
 * @property id El ID único del miembro del elenco.
 * @property known_for_department El departamento conocido del miembro del elenco.
 * @property name El nombre del miembro del elenco.
 * @property order El orden de importancia del miembro del elenco en la producción.
 * @property original_name El nombre original del miembro del elenco.
 * @property popularity La popularidad del miembro del elenco.
 * @property profile_path La ruta de la imagen de perfil del miembro del elenco.
 */
data class CastX(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)