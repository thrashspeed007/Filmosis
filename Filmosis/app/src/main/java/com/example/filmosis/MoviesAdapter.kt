package com.example.filmosis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Result

class MoviesAdapter(private val movies: List<Result>, private val onMovieClick: (Result) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView : ImageView = itemView.findViewById(R.id.moviePosterImageView)
        val movieTitle : TextView = itemView.findViewById(R.id.movieTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_horizontal_movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + movie.poster_path
        Glide.with(holder.movieImageView.context).load(imageUrl).into(holder.movieImageView)
        holder.movieTitle.text = movie.title
        holder.itemView.setOnClickListener {onMovieClick.invoke(movie)}
    }
}