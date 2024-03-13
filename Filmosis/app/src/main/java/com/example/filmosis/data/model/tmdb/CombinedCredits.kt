package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa los créditos combinados de una producción, que incluyen tanto el elenco como el equipo de producción.
 * @property cast La lista de miembros del elenco de la producción.
 * @property crew La lista de miembros del equipo de producción de la producción.
 * @property id El ID único de la producción a la que pertenecen los créditos combinados.
 */
data class CombinedCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)