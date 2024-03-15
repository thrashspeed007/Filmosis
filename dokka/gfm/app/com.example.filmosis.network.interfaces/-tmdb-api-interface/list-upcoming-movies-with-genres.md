//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[listUpcomingMoviesWithGenres](list-upcoming-movies-with-genres.md)

# listUpcomingMoviesWithGenres

[androidJvm]\

@GET(value = &quot;discover/movie?language=es-ES&amp;sort_by=popularity.desc&quot;)

abstract fun [listUpcomingMoviesWithGenres](list-upcoming-movies-with-genres.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;with_genres&quot;)genres: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;primary_release_date.gte&quot;)releaseDateGTE: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de las próximas películas con géneros específicos.

#### Return

Call que devuelve una respuesta de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que contiene las próximas películas con los géneros especificados y un mínimo de votos.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| genres | Géneros de las películas a filtrar. |
| releaseDateGTE | Fecha de lanzamiento mayor o igual que. |
