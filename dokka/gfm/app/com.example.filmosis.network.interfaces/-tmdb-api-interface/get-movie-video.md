//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getMovieVideo](get-movie-video.md)

# getMovieVideo

[androidJvm]\

@GET(value = &quot;movie/{movie_id}?language=es-ES&quot;)

abstract fun [getMovieVideo](get-movie-video.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;append_to_response&quot;)appendToResponse: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;videos&quot;): Call&lt;[MovieDetailsResponse](../../com.example.filmosis.dataclass/-movie-details-response/index.md)&gt;

Obtiene los detalles de una película específica.

#### Return

Call que devuelve una respuesta de [MovieDetailsResponse](../../com.example.filmosis.dataclass/-movie-details-response/index.md) que contiene los detalles de la película.

#### Parameters

androidJvm

| | |
|---|---|
| movieId | ID de la película. |
| apiKey | Clave de la API de TMDb. |
| appendToResponse | Parámetro opcional para especificar datos adicionales que se desean incluir en la respuesta, por defecto se incluyen los vídeos. |
