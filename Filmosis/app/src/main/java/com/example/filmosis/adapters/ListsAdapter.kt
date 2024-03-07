package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.dataclass.ListItem

class ListsAdapter(private val lists: List<ListItem>, private val onListClick: (ListItem) -> Unit) : RecyclerView.Adapter<ListsAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.itemList_nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.itemList_description)
        val dateTextView: TextView = itemView.findViewById(R.id.itemList_creationDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = lists[position]

        holder.titleTextView.text = list.listTitle
        holder.descriptionTextView.text = list.listDescription
        holder.dateTextView.text = list.listCreationDate

        holder.itemView.setOnClickListener { onListClick.invoke(list) }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}