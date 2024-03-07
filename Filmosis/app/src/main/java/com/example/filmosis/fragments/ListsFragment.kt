package com.example.filmosis.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListsAdapter
import com.example.filmosis.dataclass.ListItem
import com.example.filmosis.dataclass.ListedMovie
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.firestore.FirebaseFirestore

class ListsFragment : Fragment() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val username: String? = FirebaseInitializer.authInstance.currentUser?.email

        if (username != null) {
            fetchDocument(username)
        } else {
            Log.d("ListActivity", "No se pudo obtener el nombre de usuario.")
        }
    }

    private fun fetchDocument(username: String) {
        val docRef = firestore.collection("lists").document(username)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val data = document.data
                    if (data != null) {
                        val keys = data.keys.toList()

                        val listOfLists = keys.map { key ->
                            val listData = data[key] as? Map<*, *>
                            val listName = listData?.get("listName") as? String
                            val listDescription = listData?.get("listDescription") as? String
                            val listDate = listData?.get("listDate") as? String

                            ListItem(listName.toString(), listDescription.toString(), listDate.toString())
                        }

                        val rv = requireView().findViewById<RecyclerView>(R.id.lists_recyclerView)
                        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        rv.adapter = ListsAdapter(listOfLists) {
                            Toast.makeText(requireContext(), "*llevar a pelis de lista*", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.d("ListActivity", "El documento está vacío para el usuario $username.")
                    }
                } else {
                    Log.d("ListActivity", "No se encontró ningún documento para el usuario $username.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ListActivity", "Error al obtener el documento: $exception")
            }
    }

}