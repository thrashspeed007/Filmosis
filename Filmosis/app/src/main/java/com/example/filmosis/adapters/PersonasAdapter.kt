package com.example.filmosis.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Director
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.dataclass.Servicio

class PersonasAdapter(private val directores: List<Director>, private val onDirectorClick: (Director) -> Unit) : RecyclerView.Adapter<PersonasAdapter.PersonaViewHolder>() {

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombrePersona: TextView = itemView.findViewById(R.id.nombre_persona)
        val iconoPersona :ImageView = itemView.findViewById(R.id.icono_persona)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_personas, parent, false)
        return PersonaViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return directores.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val director = directores[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + director.profile_image_url
        Glide.with(holder.iconoPersona.context).load(imageUrl).into(holder.iconoPersona)
        holder.nombrePersona.text = director.name
        holder.itemView.setOnClickListener{onDirectorClick.invoke(director)}
    }

}
