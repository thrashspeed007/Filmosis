package com.example.filmosis.adapters

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class MoviesInListAdapter(private val movies: List<ListedMovie>, private val onMovieClick: (ListedMovie) -> Unit): RecyclerView.Adapter<MoviesInListAdapter.MovieRowViewHolder>() {
    class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val movieName : TextView = itemView.findViewById(R.id.itemMovieSearched_movieName)
        val movieDate : TextView = itemView.findViewById(R.id.itemMovieSearched_textReleaseDate)
        val movieVoteAverage : TextView = itemView.findViewById(R.id.itemMovieSearched_textAverageVote)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_searched, parent, false)
        view.setOnCreateContextMenuListener { menu, v, menuInfo ->
            val activity = view.context as? MainActivity
            activity?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu.setHeaderTitle("Opciones de película")
        }
        return MovieRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieName.text = movie.title

        holder.movieDate.text = "Fecha de salida: ${movie.releaseDate}"
        holder.movieVoteAverage.text = "Puntuación media: ${movie.averageVote}"

        holder.itemView.setOnClickListener {onMovieClick.invoke(movie)}
    }
}