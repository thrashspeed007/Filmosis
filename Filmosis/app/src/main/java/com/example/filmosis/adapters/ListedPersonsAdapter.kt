package com.example.filmosis.adapters

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.MainActivity
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Person
import com.example.filmosis.utilities.tmdb.TmdbData
import de.hdodenhof.circleimageview.CircleImageView

class ListedPersonsAdapter(private val persons: List<Person>, private val onPersonClick: (Person) -> Unit): RecyclerView.Adapter<ListedPersonsAdapter.PersonRowViewHolder>() {
    class PersonRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val personProfilePic : CircleImageView = itemView.findViewById(R.id.personSearched_profilePic)
        val personName : TextView = itemView.findViewById(R.id.personSearched_personTitle)
        val personType: TextView = itemView.findViewById(R.id.personSearched_personType)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            (v?.context as? MainActivity)?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu?.setHeaderTitle("Opciones pelicula")

            // Accion de cada item de menu...

            menu?.findItem(R.id.movieRowMenu_addTolist)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar añadir a lista*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }

            menu?.findItem(R.id.movieRowMenu_shareMovie)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar compartir pelicula*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }

            menu?.findItem(R.id.movieRowMenu_download_cover)?.setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "*implementar descargar portada*", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_searched, parent, false)
        view.setOnCreateContextMenuListener { menu, v, menuInfo ->
            val activity = view.context as? MainActivity
            activity?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu.setHeaderTitle("Opciones de película")
        }
        return PersonRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onBindViewHolder(holder: PersonRowViewHolder, position: Int) {
        val person = persons[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + person.profile_path
        holder.personName.text = person.name

        if (!person.profile_path.isNullOrEmpty()) {
            Glide.with(holder.personProfilePic.context).load(imageUrl).into(holder.personProfilePic)
        } else {
            holder.personProfilePic.setImageResource(R.drawable.logofilmosispremium)
        }

        holder.personType.text = person.known_for_department

        holder.itemView.setOnClickListener {onPersonClick.invoke(person)}
    }
}