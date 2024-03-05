package com.example.filmosis.adapters
// ListMovieAdapter.kt
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.data.model.tmdb.ListMovie

class ListMovieAdapter(private val movies: List<ListMovie>) : RecyclerView.Adapter<ListMovieAdapter.ListMovieViewHolder>() {

    inner class ListMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_movie_title)
        val directorTextView: TextView = itemView.findViewById(R.id.text_movie_director)
        val dateTextView: TextView = itemView.findViewById(R.id.text_movie_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        Log.d("ListMovieAdapter", "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list, parent, false)
        return ListMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        Log.d("ListMovieAdapter", "onBindViewHolder: position = $position")
        val movie = movies[position]

        holder.titleTextView.text = movie.title ?: ""
        holder.directorTextView.text = movie.director ?: ""
        holder.dateTextView.text = movie.date ?: ""
    }

    override fun getItemCount(): Int {
        Log.d("ListMovieAdapter", "getItemCount: ${movies.size}")
        return movies.size
    }
}

