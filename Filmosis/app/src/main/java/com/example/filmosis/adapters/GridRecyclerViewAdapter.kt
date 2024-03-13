package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.fragments.PeliculaSeleccionadaFragment

/**
 * Adaptador para mostrar una lista de películas en formato de cuadrícula en un RecyclerView.
 * @param movies Lista de películas a mostrar.
 * @param onMovieClick Acción a realizar cuando se hace clic en una película.
 */
class GridRecyclerViewAdapter(private val movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) : RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder>() {

    /**
     * Crea y devuelve un ViewHolder.
     * @param parent El ViewGroup al que se adjuntará el nuevo View.
     * @param viewType El tipo de vista del nuevo View.
     * @return Nuevo ViewHolder que contiene la vista de un elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MyViewHolder(view)
    }

    /**
     * Obtiene el número total de elementos en el conjunto de datos.
     * @return El número total de elementos.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Actualiza el contenido del ViewHolder para mostrar el elemento en la posición dada.
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posición del elemento dentro del conjunto de datos.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onMovieClick.invoke(movie) }
    }

    /**
     * ViewHolder para una fila de la RecyclerView que representa una película.
     * @param itemView Vista de la fila.
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val iconoPelicula: ImageView = itemView.findViewById(R.id.icono_pelicula)
        private val nombrePelicula: TextView = itemView.findViewById(R.id.nombre_pelicula)

        /**
         * Vincula los datos de la película con los elementos de la vista.
         * @param pelicula La película a mostrar.
         */
        fun bind(pelicula: Movie) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${pelicula.poster_path}")
                .into(iconoPelicula)
            nombrePelicula.text = pelicula.title
        }
    }
}



