package com.example.filmosis.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.filmosis.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
//        // Cogemos el textview desde la vista inflada
        val saludoUsuTextView: TextView = view.findViewById(R.id.saludoUsu)

//        HE COMENTADO ESTO PORQUE LO HE PUESTO MEJOR QUE COJA EL USERNAME DESDE LAS PREFERENCIAS QUE SE GUARDAN AL INICIAR SESIÓN, ASI NO HAY QUE HACER CONSULTA A LA DB
//        CADA VEZ QUE SE ABRE EL FRAGMENT

//        // Obtenemos el UID del usuario actual
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        val uid = currentUser?.uid
//        //Accedemos a nuestra base de datos para obtener el nombre
//        val db = FirebaseFirestore.getInstance()
//        val userRef = db.collection("users").document(uid!!)
//
//        userRef.get().addOnSuccessListener { document ->//a traves de esta funcion lambda accedemos al contenido de document, que nos permitira cosas como leer los campos
//            val userName = document.getString("name")//cogemos el nombre pasandole el campo de nuestra tabla
//            saludoUsuTextView.text = "Hi, $userName"// Establecemos el texto de saludo con el nombre de usuario
//        }

        // Accedemos a las preferencias de la aplicación para coger el nombre de usuario activo
        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val username = prefs.getString("username", null)

        saludoUsuTextView.text = "Hi, $username" // Establecemos el texto de saludo con el nombre de usuario
    }
}
