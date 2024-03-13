package com.example.filmosis.dataclass
/**
 * Clase de datos que representa un elemento de lista.
 * @param listId Identificador único de la lista.
 * @param listTitle Título de la lista.
 * @param listDescription Descripción de la lista.
 * @param listCreationDate Fecha de creación de la lista.
 */
data class ListItem(
    val listId: String,
    val listTitle: String,
    val listDescription: String,
    val listCreationDate: String
)
