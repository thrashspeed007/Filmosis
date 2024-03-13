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
import com.example.filmosis.dataclass.Network
import com.example.filmosis.fragments.PeliculaSeleccionadaFragment

/**
 * Adaptador para mostrar una lista de servicios en formato de cuadricula en un RecyclerView
 *
 * @param movies Lista de servicios a mostrar
 * **/
class GridRecyclerServiceAdapter(private val movies: List<Network>): RecyclerView.Adapter<GridRecyclerServiceAdapter.MyViewHolder>() {


    /**
     * Crea y devuelve un ViewHolder.
     * @param parent El ViewGroup al que se adjuntará el nuevo View.
     * @param viewType El tipo de vista del nuevo View.
     * @return Nuevo ViewHolder que contiene la vista de un elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_servicio, parent, false)
        return MyViewHolder(view)
    }

    /**
     * Obtiene el numero total de elementos en el conjunto de datos.
     * @return El numero total de elementos.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Actualiza el contenido del ViewHolder para mostrar el elemento en la posicion dada.
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posicion del elemento dentro del conjunto de datos.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    /**
     * ViewHolder para una fila de la RecyclerView que representa un servicio.
     * @param itemView Vista de la fila.
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val iconoPelicula: ImageView = itemView.findViewById(R.id.icono_servicio)
        private val nombrePelicula: TextView = itemView.findViewById(R.id.nombre_servicio)

        fun bind(network: Network) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${network.logo_path}")
                .into(iconoPelicula)
            nombrePelicula.text = network.name
        }
    }
}



