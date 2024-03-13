package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Crew

/**
 * Adaptador para mostrar una lista de personas (miembros del equipo) en un RecyclerView.
 * @param crewMembers Lista de miembros del equipo a mostrar.
 * @param onPersonClick Acción a realizar cuando se hace clic en una persona.
 */
class PersonasAdapter(private val crewMembers: List<Crew>, private val onPersonClick: (Crew) -> Unit): RecyclerView.Adapter<PersonasAdapter.PersonRowViewHolder>() {

    /**
     * ViewHolder para una fila de la RecyclerView que representa una persona (miembro del equipo).
     * @param itemView Vista de la fila.
     */
    class PersonRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personProfilePic: ImageView = itemView.findViewById(R.id.personSearched_profilePic)
        val personName: TextView = itemView.findViewById(R.id.personSearched_personTitle)
        val personType: TextView = itemView.findViewById(R.id.personSearched_personType)
    }

    /**
     * Crea y devuelve un ViewHolder para una fila de la RecyclerView.
     * @param parent El ViewGroup al que se adjuntará el nuevo View.
     * @param viewType El tipo de vista del nuevo View.
     * @return Nuevo ViewHolder que contiene la vista de un elemento de la lista.
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_searched, parent, false)
        return PersonRowViewHolder(view)
    }

    /**
     * Obtiene el número total de elementos en el conjunto de datos.
     * @return El número total de elementos.
     */
    override fun getItemCount(): Int {
        return crewMembers.size
    }

    /**
     * Actualiza el contenido del ViewHolder para mostrar el elemento en la posición dada.
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posición del elemento dentro del conjunto de datos.
     */
    override fun onBindViewHolder(holder: PersonRowViewHolder, position: Int) {
        val crewMember = crewMembers[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + crewMember.profile_path

        holder.personName.text = crewMember.name
        holder.personType.text = crewMember.job

        if (!crewMember.profile_path.isNullOrEmpty()) {
            Glide.with(holder.personProfilePic.context)
                .load(imageUrl)
                .into(holder.personProfilePic)
        } else {
            holder.personProfilePic.setImageResource(R.drawable.logofilmosispremium)
        }

        holder.itemView.setOnClickListener { onPersonClick.invoke(crewMember) }
    }

}
