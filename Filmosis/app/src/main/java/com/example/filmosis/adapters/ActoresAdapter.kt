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
import com.example.filmosis.data.model.tmdb.CastX

/**
 * Adaptador para mostrar la lista de actores en un RecyclerView
 *
 * @param castMembers Lista de los miembros del reparto
 * @param onPersonClick funcion lambda para manejar el click en un actor
 * **/
class ActoresAdapter(private val castMembers: List<CastX>, private val onPersonClick: (CastX) -> Unit): RecyclerView.Adapter<ActoresAdapter.ActoresRowViewHolder>() {

    /**
     * ViewHolder para una fila del RecyclerView que representa a un actor
     * @param itemView Vista de la fila
     * **/
    class ActoresRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personProfilePic: ImageView = itemView.findViewById(R.id.personSearched_profilePic)
        val personName: TextView = itemView.findViewById(R.id.personSearched_personTitle)
        val personType: TextView = itemView.findViewById(R.id.personSearched_personType)
    }


    /**
     * Crea y devuelve el ViewHolder
     *
     * @param parent El ViewGroup al que se adjuntara el nuevo View
     * @param viewType El tipo de vista del nuevo View
     * @return Nuevo ViewHolder que contiene la vista de un elemento de la lista
     * **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActoresRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_searched, parent, false)
        return ActoresRowViewHolder(view)
    }

    /**
     * Obtiene el numero total de elemtnos de la lista del reparto
     * @return El numero total de elementos
     * **/
    override fun getItemCount(): Int {
        return castMembers.size
    }

    /**
     * Actualiza el contenido del ViewHolder para mostrar el elemento en la posicion dada
     *
     * @param holder El ViewHolder que debe actualizarse
     * @param position La posicion del elemento dentro del conjunto de datos
     * **/

    override fun onBindViewHolder(holder: ActoresRowViewHolder, position: Int) {
        val castMember = castMembers[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + castMember.profile_path

        holder.personName.text = castMember.name

        when (castMember.gender) {
            1 -> {
                if (castMember.known_for_department == "Directing") {
                    holder.personType.text = "Directora"
                } else if (castMember.known_for_department == "Acting") {
                    holder.personType.text = "Actriz"
                }
            }
            else -> {
                if (castMember.known_for_department == "Directing") {
                    holder.personType.text = "Director"
                } else if (castMember.known_for_department == "Acting") {
                    holder.personType.text = "Actor"
                }
            }
        }

        if (!castMember.profile_path.isNullOrEmpty()) {
            Glide.with(holder.personProfilePic.context)
                .load(imageUrl)
                .into(holder.personProfilePic)
        } else {
            holder.personProfilePic.setImageResource(R.drawable.logofilmosispremium)
        }

        holder.itemView.setOnClickListener { onPersonClick.invoke(castMember) }
    }

}
