//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getStreamingProviders](get-streaming-providers.md)

# getStreamingProviders

[androidJvm]\

@GET(value = &quot;movie/{movie_id}/watch/providers&quot;)

abstract fun [getStreamingProviders](get-streaming-providers.md)(@Path(value = &quot;movie_id&quot;)movieId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[NetworkDetailsResponse](../../com.example.filmosis.dataclass/-network-details-response/index.md)&gt;

Obtiene los proveedores de transmisión para una película específica.

#### Return

Call que devuelve una respuesta de [NetworkDetailsResponse](../../com.example.filmosis.dataclass/-network-details-response/index.md) que contiene los proveedores de transmisión para la película.

#### Parameters

androidJvm

| | |
|---|---|
| movieId | ID de la película. |
| apiKey | Clave de la API de TMDb. |
