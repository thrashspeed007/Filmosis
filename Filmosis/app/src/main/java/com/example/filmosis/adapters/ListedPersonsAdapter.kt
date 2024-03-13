package com.example.filmosis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.MainActivity
import com.example.filmosis.R
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.model.tmdb.Person
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Adaptador para mostrar una lista de personas en un RecyclerView.
 *
 * @property persons Lista de personas que se mostrarán en el RecyclerView.
 * @property onPersonClick Función de devolución de llamada que se invocará cuando se haga clic en una persona.
 */
class ListedPersonsAdapter(private val persons: List<Person>, private val onPersonClick: (Person) -> Unit): RecyclerView.Adapter<ListedPersonsAdapter.PersonRowViewHolder>() {

    /**
     * ViewHolder para cada fila de persona en el RecyclerView.
     *
     * @param itemView La vista de cada fila de persona.
     */
    class PersonRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personProfilePic : CircleImageView = itemView.findViewById(R.id.personSearched_profilePic)
        val personName : TextView = itemView.findViewById(R.id.personSearched_personTitle)
        val personType: TextView = itemView.findViewById(R.id.personSearched_personType)
    }

    /**
     * Crea un nuevo ViewHolder para las filas de persona.
     *
     * @param parent El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene la vista de una fila de persona.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_searched, parent, false)
        view.setOnCreateContextMenuListener { menu, v, menuInfo ->
            val activity = view.context as? MainActivity
            activity?.menuInflater?.inflate(R.menu.movie_row_menu, menu)
            menu.setHeaderTitle("Opciones de película")
        }
        return PersonRowViewHolder(view)
    }

    /**
     * Devuelve el número total de elementos en la lista de personas.
     *
     * @return El número total de personas en la lista.
     */
    override fun getItemCount(): Int {
        return persons.size
    }

    /**
     * Vincula los datos de una persona específica al ViewHolder en una posición determinada.
     *
     * @param holder El ViewHolder al que se vincularán los datos.
     * @param position La posición de la persona en la lista.
     */
    override fun onBindViewHolder(holder: PersonRowViewHolder, position: Int) {
        val person = persons[position]
        val imageUrl = DatosConexion.TMDB_IMAGE_BASE_URL + person.profile_path
        holder.personName.text = person.name

        // Carga la imagen de la persona en el CircleImageView utilizando Glide.
        if (!person.profile_path.isNullOrEmpty()) {
            Glide.with(holder.personProfilePic.context).load(imageUrl).into(holder.personProfilePic)
        } else {
            holder.personProfilePic.setImageResource(R.drawable.logofilmosispremium)
        }

        // Determina el tipo de persona (actor/actriz o director/directora) y lo muestra en el TextView.
        when (person.gender) {
            1 -> {
                if (person.known_for_department == "Directing") {
                    holder.personType.text = "Directora"
                } else if (person.known_for_department == "Acting") {
                    holder.personType.text = "Actriz"
                }
            }
            else -> {
                if (person.known_for_department == "Directing") {
                    holder.personType.text = "Director"
                } else if (person.known_for_department == "Acting") {
                    holder.personType.text = "Actor"
                }
            }
        }

        // Configura el clic en la fila de persona para invocar la función de devolución de llamada.
        holder.itemView.setOnClickListener {onPersonClick.invoke(person)}
    }
}