package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa los créditos de un elenco de una producción, que incluyen tanto los miembros del elenco como el equipo de producción.
 * @property cast La lista de miembros del elenco de la producción.
 * @property crew La lista de miembros del equipo de producción de la producción.
 * @property id El ID único de la producción a la que pertenecen los créditos del elenco.
 */
data class CreditsCast(
    val cast: List<CastX>,
    val crew: List<CrewX>,
    val id: Int
)