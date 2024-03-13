package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.fragments.GenreSelectedFragment
import com.example.filmosis.utilities.app.ResourcesMapping

/**
 * Adaptador para mostrar tarjetas de géneros en un RecyclerView.
 *
 * @property genres Lista de pares (ID de género, nombre de género) que se mostrarán en las tarjetas.
 */
class GenresCardViewsAdapter(private val genres: List<Pair<Int, String>>) : RecyclerView.Adapter<GenresCardViewsAdapter.ViewHolder>() {

    /**
     * Crea un nuevo ViewHolder para las tarjetas de género.
     *
     * @param parent El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene la vista de una tarjeta de género.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_cardview_template, parent, false)
        return ViewHolder(view)
    }

    /**
     * Vincula los datos de un género específico al ViewHolder en una posición determinada.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición del género en la lista.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genreId = genres[position].first
        val genreName = genres[position].second

        // Llama al método bind del ViewHolder para vincular los datos del género.
        holder.bind(genreId, genreName)

        // Configura el clic en la tarjeta de género para abrir el fragmento correspondiente.
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, GenreSelectedFragment.newInstance(genreId, genreName))
                .addToBackStack(null)
                .commit()
        }
    }

    /**
     * Devuelve el número total de elementos en la lista de géneros.
     *
     * @return El número total de géneros en la lista.
     */
    override fun getItemCount(): Int = genres.size

    /**
     * ViewHolder para cada tarjeta de género en el RecyclerView.
     *
     * @param itemView La vista de cada tarjeta de género.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewBackground: ImageView = itemView.findViewById(R.id.imageViewBackground)
        private val defaultDrawable = R.drawable.logofilmosispremium

        /**
         * Vincula los datos de un género específico al ViewHolder.
         *
         * @param genreId ID del género.
         * @param genreName Nombre del género.
         */
        fun bind(genreId: Int, genreName: String) {
            itemView.findViewById<TextView>(R.id.textViewGenre).text = genreName

            // Obtiene la URL de la imagen asociada al género del mapa de recursos.
            val imageUrl = ResourcesMapping.genreImageMap[genreId]

            // Utiliza Glide para cargar la imagen en el ImageView de fondo de la tarjeta.
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(defaultDrawable)
                .into(imageViewBackground)
        }
    }
}
