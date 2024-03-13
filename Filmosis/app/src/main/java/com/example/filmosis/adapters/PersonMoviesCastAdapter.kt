package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Cast

/**
 * Adaptador para mostrar el reparto de películas de una persona en un RecyclerView.
 *
 * @property casts Lista de objetos Cast que representan el reparto de películas de una persona.
 * @property onCastClick Función de devolución de llamada que se invoca cuando se hace clic en un elemento del reparto de películas.
 */
class PersonMoviesCastAdapter(private val casts: List<Cast>, private val onCastClick: (Cast) -> Unit) : RecyclerView.Adapter<PersonMoviesCastAdapter.PersonMoviesCastViewHolder>() {

    /**
     * ViewHolder para cada elemento del reparto de películas en el RecyclerView.
     *
     * @param itemView La vista de cada elemento del reparto de películas.
     */
    class PersonMoviesCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePic: ImageView = itemView.findViewById(R.id.itemPersonMovieCast_moviePic)
        val movieName: TextView = itemView.findViewById(R.id.itemPersonMovieCast_MovieTitle)

        val characterLabel: TextView = itemView.findViewById(R.id.itemPersonMovieCast_characterLabel)
        val characterName: TextView = itemView.findViewById(R.id.itemPersonMovieCast_Character)
    }

    /**
     * Crea un nuevo ViewHolder para los elementos del reparto de películas.
     *
     * @param parent El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene la vista de un elemento del reparto de películas.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonMoviesCastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_movie_cast, parent, false)
        return PersonMoviesCastViewHolder(view)
    }

    /**
     * Devuelve el número total de elementos en la lista de reparto de películas.
     *
     * @return El número total de elementos en la lista de reparto de películas.
     */
    override fun getItemCount(): Int {
        return casts.size
    }

    /**
     * Vincula los datos de un elemento específico del reparto de películas al ViewHolder en una posición determinada.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición del elemento del reparto de películas en la lista.
     */
    override fun onBindViewHolder(holder: PersonMoviesCastViewHolder, position: Int) {
        val cast = casts[position]

        if (!cast.poster_path.isNullOrEmpty()) {
            Glide.with(holder.moviePic.context).load(DatosConexion.TMDB_IMAGE_BASE_URL + cast.poster_path).into(holder.moviePic)
        } else {
            holder.moviePic.setImageResource(R.drawable.logofilmosispremium)
        }

        if (!cast.title.isNullOrEmpty()) {
            holder.movieName.text = cast.title
        } else if (!cast.original_title.isNullOrEmpty()) {
            holder.movieName.text = cast.original_title
        } else if (!cast.name.isNullOrEmpty()) {
            holder.movieName.text = cast.name
        } else {
            holder.movieName.text = cast.original_name
        }

        if (!cast.character.isNullOrEmpty()) {
            holder.characterName.text = cast.character
        } else {
            holder.characterLabel.visibility = View.GONE
            holder.characterName.visibility = View.GONE
        }

        holder.itemView.setOnClickListener { onCastClick.invoke(cast) }
    }
}