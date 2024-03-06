package com.example.filmosis.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.dataclass.NetworkDetailsResponse
import com.example.filmosis.dataclass.Servicio

class ServicioAdapter(private val platforms: List<NetworkDetailsResponse>) :
    RecyclerView.Adapter<ServicioAdapter.PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_servicio, parent, false)
        return PlatformViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        val platform = platforms[position]
        holder.bind(platform)
    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    inner class PlatformViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconoPlatform: ImageView = itemView.findViewById(R.id.icono_servicio)
        private val nombrePlatform: TextView = itemView.findViewById(R.id.nombre_servicio)

        fun bind(platform: NetworkDetailsResponse) {
            Glide.with(itemView)
                .load(platform.logo_path)
                .into(iconoPlatform)
            nombrePlatform.text = platform.name
        }
    }
}
