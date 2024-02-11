package com.example.filmosis.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.filmosis.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
        // Cogemos el textview desde la vista inflada
        val saludoUsuTextView: TextView = view.findViewById(R.id.saludoUsu)

        // Obtenemos el UID del usuario actual
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid
        //Accedemos a nuestra base de datos para obtener el nombre
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(uid!!)

        userRef.get().addOnSuccessListener { document ->//a traves de esta funcion lambda accedemos al contenido de document, que nos permitira cosas como leer los campos
            val userName = document.getString("name")//cogemos el nombre pasandole el campo de nuestra tabla
            saludoUsuTextView.text = "Hi, $userName"// Establecemos el texto de saludo con el nombre de usuario
        }



    }
}
