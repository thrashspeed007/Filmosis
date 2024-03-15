//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getMovieDetailsRecuperar](get-movie-details-recuperar.md)

# getMovieDetailsRecuperar

[androidJvm]\

@GET(value = &quot;movie/{movie_id}?language=es-ES&quot;)

abstract fun [getMovieDetailsRecuperar](get-movie-details-recuperar.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[Movie](../../com.example.filmosis.data.model.tmdb/-movie/index.md)&gt;

Obtiene los detalles de una película específica.

#### Return

Call que devuelve una respuesta de [Movie](../../com.example.filmosis.data.model.tmdb/-movie/index.md) que contiene los detalles de la película.

#### Parameters

androidJvm

| | |
|---|---|
| movieId | ID de la película. |
| apiKey | Clave de la API de TMDb. |
