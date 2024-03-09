package com.example.filmosis.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListsAdapter
import com.example.filmosis.dataclass.ListItem
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

        val createListBtn : Button = requireView().findViewById(R.id.list_createListBtn)

        createListBtn.setOnClickListener {
            crearLista()
        }


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
                            val listId = listData?.get("listId").toString()
                            Log.d("ListActivity", "listId: $listId")
                            val listName = listData?.get("listName") as? String
                            Log.d("ListActivity", "listName: ${listName.toString()}")
                            val listDescription = listData?.get("listDescription") as? String
                            Log.d("ListActivity", "listDescription: ${listDescription.toString()}")
                            val listDate = listData?.get("listDate") as? String
                            Log.d("ListActivity", "listDate: ${listDate.toString()}")

                            ListItem(listId, listName.toString(), listDescription.toString(), listDate.toString())
                        }.toMutableList()

                        initListsRv(listOfLists)

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

    private fun initListsRv(lists: MutableList<ListItem>) {
        requireView().findViewById<ProgressBar>(R.id.lists_progressCircle).visibility = View.GONE

        val rv = requireView().findViewById<RecyclerView>(R.id.lists_recyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv.adapter = ListsAdapter(lists) {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, MoviesInListFragment.newInstance(it.listId))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun crearLista() {
        val dialog = CreateListFragment()
        dialog.show(requireActivity().supportFragmentManager, "createListFragment")
    }

    fun actualizarLista(listItem : ListItem) {
        val rvAdapter = requireView().findViewById<RecyclerView>(R.id.lists_recyclerView).adapter as ListsAdapter
        rvAdapter.addItem(listItem)
    }


}