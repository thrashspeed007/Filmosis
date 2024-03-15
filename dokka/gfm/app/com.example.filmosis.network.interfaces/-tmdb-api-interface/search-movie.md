//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[searchMovie](search-movie.md)

# searchMovie

[androidJvm]\

@GET(value = &quot;search/movie?language=es-ES&amp;sort_by=popularity.desc&quot;)

abstract fun [searchMovie](search-movie.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;query&quot;)query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Busca películas por un término de búsqueda.

#### Return

Call que devuelve una respuesta de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que contiene las películas que coinciden con el término de búsqueda.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| query | Término de búsqueda. |
