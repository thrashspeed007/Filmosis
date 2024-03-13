package com.example.filmosis.config

/**
 * Clase que contiene constantes relacionadas con la configuración de la conexión con la API de TMDB.
 */
class DatosConexion {
    companion object {
        /**
         * URL base de la API de The Movie Database (TMDB).
         */
        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

        /**
         * URL base de la API de The Movie Database (TMDB).
         */
        const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

        /**
         * Clave de la API de TMDB.
         */
        const val API_KEY = "3c11ed932b4a0231970e8e2f40509898"

        /**
         * Región para las consultas de TMDB.
         */
        const val REGION = "ES"
    }
}