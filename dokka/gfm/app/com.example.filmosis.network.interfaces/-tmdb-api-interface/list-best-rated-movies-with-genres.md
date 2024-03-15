//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[listBestRatedMoviesWithGenres](list-best-rated-movies-with-genres.md)

# listBestRatedMoviesWithGenres

[androidJvm]\

@GET(value = &quot;discover/movie?language=es-ES&amp;sort_by=vote_average.desc&amp;vote_count.gte=50&quot;)

abstract fun [listBestRatedMoviesWithGenres](list-best-rated-movies-with-genres.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;with_genres&quot;)genres: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de películas mejor valoradas con géneros específicos.

#### Return

Call que devuelve una respuesta de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que contiene las películas mejor valoradas filtradas por género.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
| genres | Géneros de las películas a filtrar. |
