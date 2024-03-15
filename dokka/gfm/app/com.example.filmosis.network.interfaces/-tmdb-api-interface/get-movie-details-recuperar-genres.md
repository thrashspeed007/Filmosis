//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getMovieDetailsRecuperarGenres](get-movie-details-recuperar-genres.md)

# getMovieDetailsRecuperarGenres

[androidJvm]\

@GET(value = &quot;movie/{movie_id}?language=es-ES&quot;)

abstract fun [getMovieDetailsRecuperarGenres](get-movie-details-recuperar-genres.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[Moviefr](../../com.example.filmosis.data.model.tmdb/-moviefr/index.md)&gt;

Obtiene los detalles de una película específica con los géneros recuperados.

#### Return

Call que devuelve una respuesta de [Moviefr](../../com.example.filmosis.data.model.tmdb/-moviefr/index.md) que contiene los detalles de la película con los géneros recuperados.

#### Parameters

androidJvm

| | |
|---|---|
| movieId | ID de la película. |
| apiKey | Clave de la API de TMDb. |
