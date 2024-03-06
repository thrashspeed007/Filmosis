package com.example.filmosis.adapters
// ListMovieAdapter.kt
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list, parent, false)
        return ListMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.titleTextView.text = movie.title ?: ""
        holder.directorTextView.text = movie.director ?: ""
        holder.dateTextView.text = movie.date ?: ""
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    // Método para centrar suavemente el elemento actualmente visible
    fun centerCurrentItem(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            val visibleItemCount = lastVisibleItemPosition - firstVisibleItemPosition + 1
            if (visibleItemCount == 0) return

            val centerItemPosition = firstVisibleItemPosition + visibleItemCount / 2
            val centerView = layoutManager.findViewByPosition(centerItemPosition)

            if (centerView != null) {
                val recyclerViewWidth = recyclerView.width
                val centerViewWidth = centerView.width
                val scrollToX = centerView.left - (recyclerViewWidth - centerViewWidth) / 2

                recyclerView.smoothScrollBy(scrollToX, 0)
            }
        }
    }
}


