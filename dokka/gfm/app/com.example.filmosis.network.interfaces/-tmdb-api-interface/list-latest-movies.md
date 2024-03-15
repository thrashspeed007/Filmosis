//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[listLatestMovies](list-latest-movies.md)

# listLatestMovies

[androidJvm]\

@GET(value = &quot;discover/movie?language=es-ES&amp;sort_by=release_date.desc&amp;vote_count.gte=20&quot;)

abstract fun [listLatestMovies](list-latest-movies.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;primary_release_date.lte&quot;)releaseDateLTE: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de las últimas películas con géneros específicos y un mínimo de votos.

#### Return

Call que devuelve una respuesta de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que contiene las últimas películas con los géneros especificados y un mínimo de votos.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| genres | Géneros de las películas a filtrar. |
| releaseDateLTE | Fecha de lanzamiento menor o igual que. |
