//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[listUpcomingMovies](list-upcoming-movies.md)

# listUpcomingMovies

[androidJvm]\

@GET(value = &quot;discover/movie?language=es-ES&amp;sort_by=popularity.desc&quot;)

abstract fun [listUpcomingMovies](list-upcoming-movies.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;primary_release_date.gte&quot;)releaseDateGTE: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de las próximas películas.

#### Return

Call que devuelve una lista de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que representan las próximas películas.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| releaseDateGTE | Fecha de lanzamiento mayor o igual que. |
