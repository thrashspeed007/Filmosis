package com.example.filmosis.adapters

import com.example.filmosis.data.model.tmdb.Cast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Crew


class ActoresAdapter(private val castMembers: List<Cast>, private val onPersonClick: (Cast) -> Unit): RecyclerView.Adapter<ActoresAdapter.ActoresRowViewHolder>() {

    class ActoresRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personProfilePic: ImageView = itemView.findViewById(R.id.personSearched_profilePic)
        val personName: TextView = itemView.findViewById(R.id.personSearched_personTitle)
        val personType: TextView = itemView.findViewById(R.id.personSearched_personType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActoresRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_searched, parent, false)
        return ActoresRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return castMembers.size
    }

    override fun onBindViewHolder(holder: ActoresRowViewHolder, position: Int) {
        val castMember = castMembers[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + castMember.profile_path

        holder.personName.text = castMember.name
        holder.personType.text = castMember.known_for_department

        if (!castMember.profile_path.isNullOrEmpty()) {
            Glide.with(holder.personProfilePic.context)
                .load(imageUrl)
                .into(holder.personProfilePic)
        } else {
            holder.personProfilePic.setImageResource(R.drawable.logofilmosispremium)
        }

        holder.itemView.setOnClickListener { onPersonClick.invoke(castMember) }
    }

}
