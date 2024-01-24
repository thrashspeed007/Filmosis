package com.example.filmosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.ApiInterface
import com.example.filmosis.data.UsuarioItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUsuarios()
    }

    private fun getUsuarios() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DatosConexion.BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getUsuarios()
        val textView : TextView = findViewById(R.id.textView)

        retrofitData.enqueue(object : retrofit2.Callback<List<UsuarioItem>?> {
            override fun onResponse(
                call: retrofit2.Call<List<UsuarioItem>?>,
                response: retrofit2.Response<List<UsuarioItem>?>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        val sb = java.lang.StringBuilder()
                        for (usuario in responseBody) {
                            sb.append(usuario.username + ": " + usuario.nombre + " " + usuario.apellidos + "\n")
                        }

                        textView.text = sb.toString()
                    } else {
                        android.util.Log.d("MainActivity", "Response body is null.")
                    }
                } else {
                    android.util.Log.d("MainActivity", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<UsuarioItem>?>, t: Throwable) {
                android.util.Log.d("MainActivity", "onFailure: " + t.message )
            }
        })
    }
}