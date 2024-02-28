package com.example.filmosis.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.data.model.tmdb.Result
import com.example.filmosis.dataclass.Pelicula
import com.example.filmosis.dataclass.Servicio

class GridRecyclerViewAdapter(private val movies: List<Result>) : RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder>() {
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val iconoPelicula: ImageView = itemView.findViewById(R.id.icono_pelicula)
        private val nombrePelicula: TextView = itemView.findViewById(R.id.nombre_pelicula)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener?.onItemClick(movies[position])
            }
        }

        fun bind(pelicula: Result) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${pelicula.poster_path}")
                .into(iconoPelicula)
            nombrePelicula.text = pelicula.title
        }
    }
}

//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val iconoPelicula: ImageView = itemView.findViewById(R.id.icono_pelicula)
//        private val nombrePelicula: TextView = itemView.findViewById(R.id.nombre_pelicula)
//
//        fun bind(pelicula: Result) {
//            Glide.with(itemView.context)
//                .load("https://image.tmdb.org/t/p/w500${pelicula.poster_path}")
//                .into(iconoPelicula)
//            nombrePelicula.text = pelicula.title
//        }
//    }


