package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R
import com.example.filmosis.utilities.app.ResourcesMapping

class GenresAdapter(private val genres: List<Pair<Int, String>>) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_cardview_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genreId = genres[position].first
        val genreName = genres[position].second
        holder.bind(genreId, genreName)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "id: $genreId, genero: $genreName", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = genres.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewBackground: ImageView = itemView.findViewById(R.id.imageViewBackground)
        private val defaultDrawable = R.drawable.logofilmosispremium

        fun bind(genreId: Int, genreName: String) {
            val newGenreDrawable = ResourcesMapping.genreImageMap[genreId]

            itemView.findViewById<TextView>(R.id.textViewGenre).text = genreName

            if (newGenreDrawable != null) {
                imageViewBackground.setImageResource(newGenreDrawable)
            } else {
                imageViewBackground.setImageResource(defaultDrawable)
            }
        }
    }
}
