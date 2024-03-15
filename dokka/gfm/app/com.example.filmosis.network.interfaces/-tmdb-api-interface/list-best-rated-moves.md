//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[listBestRatedMoves](list-best-rated-moves.md)

# listBestRatedMoves

[androidJvm]\

@GET(value = &quot;discover/movie?include_adult=false&amp;include_video=false&amp;language=es-ES&amp;page=1&amp;sort_by=vote_average.desc&amp;without_genres=99,10755&amp;vote_count.gte=200&quot;)

abstract fun [listBestRatedMoves](list-best-rated-moves.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md)&gt;

Obtiene una lista de las mejores películas con un mínimo de votos y excluyendo ciertos géneros.

#### Return

Call que devuelve una respuesta de [MoviesPage](../../com.example.filmosis.data.model.tmdb/-movies-page/index.md) que contiene las mejores películas según la calificación de los usuarios y otros criterios de filtrado.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las películas. |
