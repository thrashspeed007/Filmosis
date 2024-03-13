package com.example.filmosis.dataclass

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la respuesta de detalles de película recibida desde la API.
 * @param videos Objeto que contiene los videos asociados a la película.
 */
data class MovieDetailsResponse(
    @SerializedName("videos") val videos: Videos
)

/**
 * Clase de datos que representa la lista de videos asociados a una película.
 * @param results Lista de objetos Video.
 */
data class Videos(
    @SerializedName("results") val results: List<Video>
)

/**
 * Clase de datos que representa un video asociado a una película.
 * @param key Clave única del video.
 */
data class Video(
    @SerializedName("key") val key: String
)