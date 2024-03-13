package com.example.filmosis.dataclass

import com.google.gson.annotations.SerializedName

/**
 * Clase de datos que representa la respuesta de detalles de red de streaming recibida desde la API.
 * @param id Identificador único de la red de streaming.
 * @param results Mapa que contiene los detalles de la red de streaming por país.
 */

data class NetworkDetailsResponse(
    val id: Int,
    val results: Map<String, PaisServicios>
)

/**
 * Clase de datos que representa los servicios de streaming ofrecidos por país.
 * @param link Enlace de la red de streaming.
 * @param flatrate Lista de servicios de suscripción plana.
 * @param rent Lista de servicios de alquiler.
 * @param buy Lista de servicios de compra.
 */
data class PaisServicios(
    val link: String,
    val flatrate: List<Servicio>,
    val rent: List<Servicio>,
    val buy: List<Servicio>
)

/**
 * Clase de datos que representa un servicio de streaming ofrecido.
 * @param logo_path Ruta del logo del proveedor del servicio.
 * @param provider_id Identificador del proveedor del servicio.
 * @param provider_name Nombre del proveedor del servicio.
 * @param display_priority Prioridad de visualización del servicio.
 */
data class Servicio(
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String,
    val display_priority: Int
)

/**
 * Clase de datos que representa una red de streaming.
 * @param id Identificador único de la red de streaming.
 * @param name Nombre de la red de streaming.
 * @param logo_path Ruta del logo de la red de streaming.
 * @param headquarters Sede de la red de streaming.
 * @param homepage Página web de la red de streaming.
 * @param origin_country País de origen de la red de streaming.
 */

data class Network(
    val id: Int,
    val name: String,
    val logo_path: String,
    val headquarters: String,
    val homepage: String,
    val origin_country: String
)






