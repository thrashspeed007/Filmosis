package com.example.filmosis.utilities.tmdb

import java.io.Serializable

enum class TmdbQuery : Serializable {
    LIST_POPULAR,
    LIST_UPCOMING,
    LIST_RECOMMENDED,
    SEARCH_MOVIE
}