//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getPersonDetails](get-person-details.md)

# getPersonDetails

[androidJvm]\

@GET(value = &quot;person/{person_id}?language=es-ES&quot;)

abstract fun [getPersonDetails](get-person-details.md)(@Path(value = &quot;person_id&quot;)personId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[PersonDetails](../../com.example.filmosis.data.model.tmdb/-person-details/index.md)&gt;

Obtiene los detalles de una persona específica.

#### Return

Call que devuelve una respuesta de [PersonDetails](../../com.example.filmosis.data.model.tmdb/-person-details/index.md) que contiene los detalles de la persona.

#### Parameters

androidJvm

| | |
|---|---|
| personId | ID de la persona. |
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener los detalles de la persona. |
