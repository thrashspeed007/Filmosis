//[app](../../../index.md)/[com.example.filmosis.network.interfaces](../index.md)/[TmdbApiInterface](index.md)/[getNetworkDetails](get-network-details.md)

# getNetworkDetails

[androidJvm]\

@GET(value = &quot;network/{id}&quot;)

abstract fun [getNetworkDetails](get-network-details.md)(@Path(value = &quot;id&quot;)networkId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), @Query(value = &quot;api_key&quot;)apiKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Call&lt;[Network](../../com.example.filmosis.dataclass/-network/index.md)&gt;

Obtiene los detalles de una red específica.

#### Return

Call que devuelve una respuesta de [Network](../../com.example.filmosis.dataclass/-network/index.md) que contiene los detalles de la red.

#### Parameters

androidJvm

| | |
|---|---|
| networkId | ID de la red. |
| apiKey | Clave de la API de TMDb. |
