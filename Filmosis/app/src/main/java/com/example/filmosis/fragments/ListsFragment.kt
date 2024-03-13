package com.example.filmosis.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.adapters.ListsAdapter
import com.example.filmosis.dataclass.ListItem
import com.example.filmosis.init.FirebaseInitializer
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Fragmento para mostrar las listas del usuario
 * permite al usuario crear, ver y eliminar listas
 *
 * @property firestore Acceso a la instancia de Firestore para interactuar con la base de datos Firebase
 * @property rootView vista raiz del fragmento
 * **/

class ListsFragment : Fragment() {

    private val firestore: FirebaseFirestore = FirebaseInitializer.firestoreInstance

    private var rootView: View? = null

    /**
     * Infla la vista del fragmento
     *
     * @param inflater Se utiliza para inflar la vista
     * @param container El contenedor al que se agrega la visat
     * @param savedInstanceState Información previamente guardada del fragmento.
     * @return La vista inflada del fragmento.
     * **/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_lists, container, false)
        }

        return rootView
    }

    /**
     *Inicia la configuracion del fragmetno
     *
     * @param view La vista inflada del fragmento
     * @param savedInstanceState Información previamente guardada del fragmento, si existe.
     * **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    /**
     * Configura la vista del fragmento
     * Verifica la existencia del usuario
     * Configura el boton para crear una nueva lista
     * Si el usuario no esta autenticado, se registrara un mensaje de error
     * **/
    private fun setup() {
        val username: String? = FirebaseInitializer.authInstance.currentUser?.email

        val createListBtn : Button? = rootView?.findViewById(R.id.list_createListBtn)

        createListBtn?.setOnClickListener {
            crearLista()
        }

        if (username != null) {
            fetchDocument(username)
        } else {
            Log.d("ListActivity", "No se pudo obtener el nombre de usuario.")
        }
    }

    /**
     * Obtiene y muestra las listas de las peliculas del usaurio desde Firestore
     *
     * @param username el nombre del usaurio actual
     *
     * **/
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

                        rootView?.findViewById<ProgressBar>(R.id.lists_progressCircle)?.visibility = View.GONE

                        if (listOfLists.isEmpty()) {
                            rootView?.findViewById<TextView>(R.id.lists_errorTextView)?.text = "Todavía no tienes creada ninguna lista.\nCrea una con el botón de abajo."
                        } else {
                            rootView?.findViewById<TextView>(R.id.lists_errorTextView)?.visibility = View.GONE
                            initListsRv(listOfLists)
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

    /**
     * Inicializa y configura el RecyclerView para mostrar las listas de peliculas.
     *
     * @param lists La lista de elementos ListItem que representan las listas de peliculas.
     * **/
    private fun initListsRv(lists: MutableList<ListItem>) {
        val rv = rootView?.findViewById<RecyclerView>(R.id.lists_recyclerView)
        rv?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv?.adapter = ListsAdapter(
            lists,
            onListClick = {
                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, MoviesInListFragment.newInstance(it.listId))
                transaction.addToBackStack(null)
                transaction.commit()
            },
            onDeleteClick = {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Confirmar")
                builder.setMessage("¿Estás seguro de que quieres eliminar la lista?")

                builder.setPositiveButton("Si") { _, _ ->
                    deleteListFromFirestore(it.listId)
                    val adapter = rv?.adapter as? ListsAdapter
                    adapter?.deleteItemByListId(it.listId)
                }

                builder.setNegativeButton("No") { _, _ ->

                }

                val alertDialog = builder.create()
                alertDialog.show()
            }
        )
    }

    /**
     * Elimina una lista de peliculas de Firestore
     *
     * @param listId El id de la lista a eliminar
     * **/

    private fun deleteListFromFirestore(listId: String) {
        val userEmail = FirebaseInitializer.authInstance.currentUser?.email.toString()
        val listsRef = firestore.collection("lists").document(userEmail)

        val updates = hashMapOf<String, Any>(
            "lista_$listId" to FieldValue.delete()
        )

        listsRef.update(updates)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Lista eliminada", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Error al eliminar la lista: ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    /**
     * Muestra el dialogo para crear una nueva lista
     * **/

    private fun crearLista() {
        val dialog = CreateListFragment()
        dialog.show(requireActivity().supportFragmentManager, "createListFragment")
    }


}