//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getRecommendedMovies](get-recommended-movies.md)

# getRecommendedMovies

[androidJvm]\

@GET(value = &quot;movie/{movie_id}/recommendations?language=es-ES&quot;)

abstract fun [getRecommendedMovies](get-recommended-movies.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de las próximas recomendadas.

#### Return

Call que devuelve una lista de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que representan las próximas películas.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| movieId | El id de la película sobre la que se recomendar películas similares |
