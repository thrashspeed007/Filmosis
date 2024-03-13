package com.example.filmosis.data.model.tmdb

/**
 * Clase que representa una página de resultados de personas obtenida de la API de TMDB.
 * @property page El número de página actual.
 * @property results La lista de personas en la página actual.
 * @property total_pages El número total de páginas disponibles.
 * @property total_results El número total de resultados de personas.
 */
data class PersonsPage(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)