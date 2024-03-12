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
import com.example.filmosis.dataclass.Network
import com.example.filmosis.dataclass.Servicio

//Clase modificada para que pueda recibir como una lista de Servicio como una lista de NetworkDetails
/**
 * Adapter para manejar la visualizacion de diferentes elementos en un RecyclerView.
 * Este dapter maneja elementos de tipo Servicio y Network
 *
 * @param items Le pasamos la lista de cualquier clase, en nuestro caso de Servicio o de NetworkDetails
 *
 * **/
class ServicioAdapter(private val items: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SERVICIO = 1
        private const val TYPE_NETWORK_DETAILS = 2
    }

    /**
     * Crea y devuelve un [RecyclerView.ViewHolder] correspondiente al tipo de vista.
     *
     * @param parent El ViewGroup al que se añadirá la nueva vista.
     * @param viewType El tipo de la nueva vista.
     * @return El nuevo [RecyclerView.ViewHolder] que contiene la vista creada.
     * @throws IllegalArgumentException Si el viewType no es válido.
     */
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SERVICIO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_servicio, parent, false)
                ServicioViewHolder(view)
            }
            TYPE_NETWORK_DETAILS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_servicio, parent, false)
                NetworkDetailsViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ServicioViewHolder -> {
                val servicio = items[position] as Servicio
                holder.bind(servicio)
            }
            is NetworkDetailsViewHolder -> {
                val networkDetails = items[position] as Network
                holder.bind(networkDetails)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Servicio -> TYPE_SERVICIO
            is Network -> TYPE_NETWORK_DETAILS
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    inner class ServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconoServicio: ImageView = itemView.findViewById(R.id.icono_servicio)
        private val nombreServicio: TextView = itemView.findViewById(R.id.nombre_servicio)

        fun bind(servicio: Servicio) {
            val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + servicio.logo_path
            Glide.with(itemView)
                .load(imageUrl)
                .into(iconoServicio)
            nombreServicio.text = servicio.provider_name
        }
    }


    /**
     * ViewHolder para los elementos de tipo Network
     **/
    inner class NetworkDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.nombre_servicio)
        private val icono: ImageView = itemView.findViewById(R.id.icono_servicio)
        /**
         *Metodo para vincular los datos del Network con sus vistas correspondientes
         **/
        fun bind(networkDetails: Network) {
            val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + networkDetails.logo_path
            Glide.with(itemView)
                .load(imageUrl)
                .into(icono)
            name.text = networkDetails.name

        }
    }
}
