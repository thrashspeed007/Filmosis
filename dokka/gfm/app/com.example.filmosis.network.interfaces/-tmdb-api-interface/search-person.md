//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[searchPerson](search-person.md)

# searchPerson

[androidJvm]\

@GET(value = &quot;search/person?language=es-ES&amp;page=1&quot;)

abstract fun [searchPerson](search-person.md)(@Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;query&quot;)query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[PersonsPage](../../com.example.filmosis.data.model.tmdb/-persons-page/index.md)&gt;

Busca personas por un término de búsqueda.

#### Return

Call que devuelve una respuesta de [PersonsPage](../../com.example.filmosis.data.model.tmdb/-persons-page/index.md) que contiene las personas que coinciden con el término de búsqueda.

#### Parameters

androidJvm

| | |
|---|---|
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener las personas. |
| query | Término de búsqueda. |
