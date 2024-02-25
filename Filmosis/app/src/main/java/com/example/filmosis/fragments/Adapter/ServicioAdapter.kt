package com.example.filmosis.fragments.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmosis.R

class ServicioAdapter(private val context: Context, private val servicios: List<Servicio>) :
    RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_servicio, parent, false)
        return ServicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicioViewHolder, position: Int) {
        val servicio = servicios[position]
        holder.bind(servicio)
    }

    override fun getItemCount(): Int {
        return servicios.size
    }

    inner class ServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconoServicio: ImageView = itemView.findViewById(R.id.icono_servicio)
        private val nombreServicio: TextView = itemView.findViewById(R.id.nombre_servicio)

        fun bind(servicio: Servicio) {
            iconoServicio.setImageResource(servicio.icono)
            nombreServicio.text = servicio.nombre
        }
    }
}
