package com.example.filmosis.adapters

import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.MainActivity
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.dataclass.ListedMovie
import com.example.filmosis.utilities.tmdb.TmdbData

/**
 * Adaptador para mostrar una lista de películas en un RecyclerView con opciones adicionales en un menú contextual.
 * @param movies Lista de películas a mostrar.
 * @param onMovieClick Acción a realizar cuando se hace clic en una película.
 * @param isDeleteable Indica si se permite eliminar películas de la lista (predeterminado: false).
 * @param onDeleteMovie Acción a realizar cuando se elimina una película (predeterminado: vacío).
 */
class MoviesInListAdapter(private val movies: MutableList<ListedMovie>, private val onMovieClick: (ListedMovie) -> Unit, private val isDeleteable: Boolean = false, private val onDeleteMovie: (ListedMovie) -> Unit = {}): RecyclerView.Adapter<MoviesInListAdapter.MovieRowViewHolder>() {

    /**
     * ViewHolder para una fila de la RecyclerView que representa una película.
     * @param itemView Vista de la fila.
     */
    class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val movieName : TextView = itemView.findViewById(R.id.itemMovieSearched_movieName)
        val moviePoster: ImageView = itemView.findViewById(R.id.itemMovieSearched_imagePoster)
        val movieDate : TextView = itemView.findViewById(R.id.itemMovieSearched_textReleaseDate)
        val movieVoteAverage : TextView = itemView.findViewById(R.id.itemMovieSearched_textAverageVote)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        /**
         * Configura el menú contextual para la fila de la película.
         * @param menu El menú contextual.
         * @param v La vista asociada a la fila de la película.
         * @param menuInfo Información sobre el menú contextual.
         */

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            (v?.context as? MainActivity)?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu?.setHeaderTitle("Opciones pelicula")

            // Accion de cada item de menu...

            menu?.findItem(R.id.movieRowMenu_addTolist)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar añadir a lista*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }

            menu?.findItem(R.id.movieRowMenu_shareMovie)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar compartir pelicula*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }

            menu?.findItem(R.id.movieRowMenu_download_cover)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar descargar portada*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }
        }
    }

    /**
     * Crea y devuelve un ViewHolder para una fila de la RecyclerView.
     * @param parent El ViewGroup al que se adjuntará el nuevo View.
     * @param viewType El tipo de vista del nuevo View.
     * @return Nuevo ViewHolder que contiene la vista de un elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_searched, parent, false)
        view.setOnCreateContextMenuListener { menu, v, menuInfo ->
            val activity = view.context as? MainActivity
            activity?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu.setHeaderTitle("Opciones de película")
        }
        return MovieRowViewHolder(view)
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
    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        val movie = movies[position]
        // Comprobar si los items se pueden borrar, si no es asi ocultar el botón...
        if (isDeleteable) {
            holder.itemView.findViewById<ImageButton>(R.id.itemMovieSearched_deleteButton).setOnClickListener {
                onDeleteMovie.invoke(movie)
            }
        } else {
            holder.itemView.findViewById<ImageButton>(R.id.itemMovieSearched_deleteButton).visibility = View.INVISIBLE
        }

        if (movie.poster_path.isNotEmpty()) {
            val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + movie.poster_path
            Glide.with(holder.itemView.context).load(imageUrl).into(holder.moviePoster)
        }

        holder.movieName.text = movie.title

        holder.movieDate.text = "Fecha de salida: ${movie.releaseDate}"
        holder.movieVoteAverage.text = "Puntuación media: ${movie.averageVote}"

        holder.itemView.setOnClickListener {
            onMovieClick.invoke(movie)
        }
    }

    /**
     * Elimina un elemento de la lista de películas por su ID.
     * @param movieId El ID de la película a eliminar.
     */
    fun deleteItemByMovieId(movieId: Int) {
        val position = movies.indexOfFirst { it.id == movieId }
        if (position != -1) {
            movies.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, movies.size)
        }
    }
}