package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.dataclass.ListItem
import com.example.filmosis.utilities.firebase.FirestoreImageManager

/**
 * Adaptador para mostrar elementos de listas en un RecyclerView.
 *
 * @property lists Lista mutable de ListItem que se mostrarán en el RecyclerView.
 * @property onListClick Función de devolución de llamada que se invocará cuando se haga clic en una lista.
 * @property onDeleteClick Función de devolución de llamada que se invocará cuando se haga clic en el botón de eliminar de una lista.
 */
class ListsAdapter(private val lists: MutableList<ListItem>, private val onListClick: (ListItem) -> Unit, private val onDeleteClick: (ListItem) -> Unit) : RecyclerView.Adapter<ListsAdapter.ListViewHolder>() {

    /**
     * ViewHolder para cada fila de lista en el RecyclerView.
     *
     * @param itemView La vista de cada fila de lista.
     */

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemList_imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.itemList_nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.itemList_description)
        val dateTextView: TextView = itemView.findViewById(R.id.itemList_creationDateTextView)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.itemList_deleteBtn)
    }

    /**
     * Crea un nuevo ViewHolder para las filas de lista.
     *
     * @param parent El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene la vista de una fila de lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    /**
     * Vincula los datos de una lista específica al ViewHolder en una posición determinada.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición de la lista en la lista mutable.
     */
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = lists[position]

        // Carga la imagen de la lista en el ImageView utilizando Glide.
        Glide.with(holder.itemView.context).load(FirestoreImageManager.getTemporaryImageUri()?.toUri()).into(holder.imageView)

        // Asigna los datos de la lista a los elementos de la vista del ViewHolder.
        holder.titleTextView.text = list.listTitle
        holder.descriptionTextView.text = list.listDescription
        holder.dateTextView.text = list.listCreationDate

        // Configura el clic en el botón de eliminar de la lista para invocar la función de devolución de llamada correspondiente.
        holder.deleteBtn.setOnClickListener {
            onDeleteClick.invoke(list)
        }

        // Configura el clic en la fila de lista para invocar la función de devolución de llamada.
        holder.itemView.setOnClickListener { onListClick.invoke(list) }
    }

    /**
     * Devuelve el número total de elementos en la lista mutable de listas.
     *
     * @return El número total de listas en la lista mutable.
     */
    override fun getItemCount(): Int {
        return lists.size
    }

    /**
     * Elimina un elemento de la lista mutable por su identificador de lista y notifica al adaptador sobre el cambio.
     *
     * @param listId El identificador de la lista que se eliminará.
     */
    fun deleteItemByListId(listId: String) {
        val position = lists.indexOfFirst { it.listId == listId } // Assuming 'listId' is a property in your list items
        if (position != -1) {
            lists.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, lists.size)
        }
    }
}