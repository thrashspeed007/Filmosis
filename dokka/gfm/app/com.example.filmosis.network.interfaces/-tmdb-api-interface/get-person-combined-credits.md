//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getPersonCombinedCredits](get-person-combined-credits.md)

# getPersonCombinedCredits

[androidJvm]\

@GET(value = &quot;person/{person_id}/combined_credits?language=es-ES&quot;)

abstract fun [getPersonCombinedCredits](get-person-combined-credits.md)(@Path(value = &quot;person_id&quot;)personId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Query(value = &quot;region&quot;)region: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[CombinedCredits](../../com.example.filmosis.data.model.tmdb/-combined-credits/index.md)&gt;

Obtiene los créditos combinados de una persona específica.

#### Return

Call que devuelve una respuesta de [CombinedCredits](../../com.example.filmosis.data.model.tmdb/-combined-credits/index.md) que contiene los créditos combinados de la persona.

#### Parameters

androidJvm

| | |
|---|---|
| personId | ID de la persona. |
| apiKey | Clave de la API de TMDb. |
| region | Región para la que se desean obtener los créditos. |
