package com.example.filmosis.data.access.tmdb

import android.util.Log
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Cast
import com.example.filmosis.data.model.tmdb.CombinedCredits
import com.example.filmosis.data.model.tmdb.Person
import com.example.filmosis.data.model.tmdb.PersonDetails
import com.example.filmosis.data.model.tmdb.PersonsPage
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Clase que proporciona métodos para acceder a datos relacionados con personas desde la API de TMDb.
 * Se utiliza la interfaz TmdbApiInterface donde se declaran las consultas con los parámetros necesarios.
 *
 * @constructor Crea un objeto PersonsAccess.
 */
class PersonsAccess {

    /**
     * Obtiene los créditos combinados de una persona, que incluyen películas y programas de televisión en los que ha participado.
     *
     * @param personId Identificador de la persona.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getPersonCombinedCredits(personId: Int, callback: (List<Cast>) -> Unit) {
        val call = RetrofitService.tmdbApi.getPersonCombinedCredits(personId, DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<CombinedCredits> {
            override fun onFailure(call: Call<CombinedCredits>, t: Throwable) {
                Log.d("PersonsAccess", "getPersonCombinedCredits onFailure: " + t.message)
            }

            override fun onResponse(call: Call<CombinedCredits>, response: Response<CombinedCredits>) {
                val casts = response.body()?.cast

                if (casts != null) {
                    callback.invoke(casts)
                }
            }
        })
    }

    /**
     * Busca personas basadas en una consulta proporcionada.
     *
     * @param query Consulta de búsqueda.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun searchPerson(query: String, callback: (List<Person>) -> Unit) {
        val call = RetrofitService.tmdbApi.searchPerson(DatosConexion.API_KEY, DatosConexion.REGION, query)

        call.enqueue(object : Callback<PersonsPage> {
            override fun onFailure(call: Call<PersonsPage>, t: Throwable) {
                Log.d("PersonsAccess", "searchPerson onFailure: " + t.message)
            }

            override fun onResponse(call: Call<PersonsPage>, response: Response<PersonsPage>) {
                val people = response.body()?.results

                if (people != null) {
                    callback.invoke(people)
                }
            }
        })
    }

    /**
     * Obtiene los detalles de una persona específica.
     *
     * @param personId Identificador de la persona.
     * @param callback Función de devolución de llamada que se invocará cuando se obtengan los datos.
     */
    fun getPersonDetails(personId: Int, callback: (PersonDetails) -> Unit) {
        val call = RetrofitService.tmdbApi.getPersonDetails(personId, DatosConexion.API_KEY, DatosConexion.REGION)

        call.enqueue(object : Callback<PersonDetails> {
            override fun onFailure(call: Call<PersonDetails>, t: Throwable) {
                Log.d("PersonsAccess", "getPersonDetails onFailure: " + t.message)
            }

            override fun onResponse(call: Call<PersonDetails>, response: Response<PersonDetails>) {
                val personDetails = response.body()

                if (personDetails != null) {
                    callback.invoke(personDetails)
                }
            }
        })
    }
}