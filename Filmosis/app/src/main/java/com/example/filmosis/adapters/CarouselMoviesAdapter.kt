package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Movie

/**
 * Adaptador para mostrar una lista de películas en un RecyclerView de tipo carrusel.
 *
 * @property movies Lista de películas que se mostrarán en el carrusel.
 * @property onMovieClick Función de devolución de llamada que se invocará cuando se haga clic en una película.
 */
class CarouselMoviesAdapter(private val movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) : RecyclerView.Adapter<CarouselMoviesAdapter.CarouselMoviesViewHolder>() {

    /**
     * ViewHolder para cada elemento de la lista de películas en el carrusel.
     *
     * @param itemView La vista de cada elemento de la lista.
     */
    class CarouselMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView : ImageView = itemView.findViewById(R.id.carousel_posterImageView)
    }

    /**
     * ViewHolder para cada elemento de la lista de películas en el carrusel.
     *
     * @param itemView La vista de cada elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_movie_poster_item, parent, false)
        return CarouselMoviesViewHolder(view)
    }

    /**
     * Devuelve el número total de elementos en la lista de películas.
     *
     * @return El número total de películas en la lista.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Vincula los datos de una película específica al ViewHolder.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición de la película en la lista.
     */
    override fun onBindViewHolder(holder: CarouselMoviesViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + movie.poster_path

        Glide.with(holder.movieImageView.context).load(imageUrl).into(holder.movieImageView)
        holder.itemView.setOnClickListener {onMovieClick.invoke(movie)}
    }
}