package com.example.filmosis.data.access.filmosis

import android.widget.ArrayAdapter
import com.example.filmosis.data.model.filmosis.UsuarioItem
import com.example.filmosis.network.RetrofitService
import retrofit2.Call
import retrofit2.Response

class UsersAccess {

    fun getUsers(callback: (List<String>) -> Unit) {
        val call = RetrofitService.filmosisApi.getUsuarios()
        val usersList: ArrayList<String> = ArrayList()

        call.enqueue(object : retrofit2.Callback<List<UsuarioItem>>{
            override fun onFailure(call: Call<List<UsuarioItem>>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }

            override fun onResponse(
                call: Call<List<UsuarioItem>>,
                response: Response<List<UsuarioItem>>
            ) {
                val responseBody = response.body()

                if (responseBody != null) {
                    for (user in responseBody) {
                        usersList.add(user.username + ": " + user.nombre + " " + user.apellidos)
                    }

                    callback.invoke(usersList)
                } else {
                    android.util.Log.d("MainActivity", "onFailure: responseBody is null" )
                }
            }
        })
    }
}