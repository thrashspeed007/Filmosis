//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getMovieCredits2](get-movie-credits2.md)

# getMovieCredits2

[androidJvm]\

@GET(value = &quot;movie/{movie_id}/credits?language=es-ES&quot;)

abstract fun [getMovieCredits2](get-movie-credits2.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[CreditsCast](../../com.example.filmosis.data.model.tmdb/-credits-cast/index.md)&gt;

Obtiene los créditos de una película específica (Versión alternativa para los actores).

#### Return

Call que devuelve una respuesta de [CreditsCast](../../com.example.filmosis.data.model.tmdb/-credits-cast/index.md) que contiene los créditos de la película.

#### Parameters

androidJvm

| | |
|---|---|
| movieId | ID de la película. |
| apiKey | Clave de la API de TMDb. |
