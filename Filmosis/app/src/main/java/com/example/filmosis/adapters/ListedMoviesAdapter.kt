package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.utilities.tmdb.TmdbData

/**
 * Adaptador para mostrar una lista de películas en un RecyclerView.
 *
 * @property movies Lista de películas que se mostrarán en el RecyclerView.
 * @property onMovieClick Función de devolución de llamada que se invocará cuando se haga clic en una película.
 */
class ListedMoviesAdapter(private val movies: List<Movie>, private val onMovieClick: (Movie) -> Unit) : RecyclerView.Adapter<ListedMoviesAdapter.MovieRowViewHolder>() {

    /**
     * ViewHolder para cada fila de película en el RecyclerView.
     *
     * @param itemView La vista de cada fila de película.
     */
    class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView : ImageView = itemView.findViewById(R.id.itemMovieSearched_imagePoster)
        val movieName : TextView = itemView.findViewById(R.id.itemMovieSearched_movieName)
        val movieGenres : TextView = itemView.findViewById(R.id.itemMovieSearched_movieGenres)
        val movieDate : TextView = itemView.findViewById(R.id.itemMovieSearched_textReleaseDate)
        val movieVoteAverage : TextView = itemView.findViewById(R.id.itemMovieSearched_textAverageVote)
    }

    /**
     * Crea un nuevo ViewHolder para las filas de película.
     *
     * @param parent El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene la vista de una fila de película.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_searched, parent, false)
        return MovieRowViewHolder(view)
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
     * Vincula los datos de una película específica al ViewHolder en una posición determinada.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición de la película en la lista.
     */
    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        val movie = movies[position]

        // Ocultar botón de ocultar ya que no tiene que tener al buscar ...
        holder.itemView.findViewById<ImageButton>(R.id.itemMovieSearched_deleteButton).visibility = View.INVISIBLE

        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + movie.poster_path
        holder.movieName.text = movie.title

        // Carga la imagen de la película en el ImageView utilizando Glide.
        if (!movie.poster_path.isNullOrEmpty()) {
            Glide.with(holder.movieImageView.context).load(imageUrl).into(holder.movieImageView)
        } else {
            holder.movieImageView.setImageResource(R.drawable.logofilmosispremium)
        }

        // Obtiene los nombres de los géneros de la película y los muestra en el TextView.
        val genresString: ArrayList<String> = ArrayList()
        for (id in movie.genre_ids) {
            for (genrePair in TmdbData.movieGenresIds) {
                if (genrePair.first == id) {
                    genresString.add(genrePair.second)
                    break
                }
            }
        }

        holder.movieGenres.text = genresString.joinToString(", ")

        holder.movieDate.text = "Fecha de salida: ${movie.release_date}"
        holder.movieVoteAverage.text = "Puntuación media: ${movie.vote_average}"

        // Configura el clic en la fila de película para invocar la función de devolución de llamada.
        holder.itemView.setOnClickListener {onMovieClick.invoke(movie)}
    }
}