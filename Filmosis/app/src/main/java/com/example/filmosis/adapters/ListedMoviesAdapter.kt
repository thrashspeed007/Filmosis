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
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.utilities.tmdb.TmdbData

class ListedMoviesAdapter(private val movies: List<Result>, private val onMovieClick: (Result) -> Unit): RecyclerView.Adapter<ListedMoviesAdapter.MovieRowViewHolder>() {
    class MovieRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView : ImageView = itemView.findViewById(R.id.itemMovieSearched_imagePoster)
        val movieName : TextView = itemView.findViewById(R.id.itemMovieSearched_movieName)
        val movieGenres : TextView = itemView.findViewById(R.id.itemMovieSearched_movieGenres)
        val movieDate : TextView = itemView.findViewById(R.id.itemMovieSearched_textReleaseDate)
        val movieVoteAverage : TextView = itemView.findViewById(R.id.itemMovieSearched_textAverageVote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_searched, parent, false)
        return MovieRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + movie.poster_path
        holder.movieName.text = movie.title

        if (!movie.poster_path.isNullOrEmpty()) {
            Glide.with(holder.movieImageView.context).load(imageUrl).into(holder.movieImageView)
        } else {
            holder.movieImageView.setImageResource(R.drawable.logofilmosispremium)
        }

        var genresString: ArrayList<String> = ArrayList()
        for (id in movie.genre_ids) {
            for (genrePair in TmdbData.movieGenresIds) {
                if (genrePair.first == id) {
                    genresString.add(genrePair.second)
                    break
                }
            }
        }

        holder.movieGenres.text = genresString.joinToString(", ")

        holder.movieDate.text = movie.release_date
        holder.movieVoteAverage.text = movie.vote_average.toString()

        holder.itemView.setOnClickListener {onMovieClick.invoke(movie)}
    }
}