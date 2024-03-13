package com.example.filmosis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmosis.R
import com.example.filmosis.adapters.PersonMoviesCastAdapter
import com.example.filmosis.config.DatosConexion
import com.example.filmosis.data.access.tmdb.PersonsAccess
import com.example.filmosis.data.model.tmdb.Cast

/**
 * Fragmento que muestra los detalles de una persona, incluyendo su información personal y las películas en las que ha participado.
 *
 * @property personsAccess PersonsAccess para realizar consultas a la API de TMDB sobre datos de personas
 * @see PersonsAccess
 */
class PersonDetailsFragment : Fragment() {
    private val personsAccess = PersonsAccess()

    companion object {
        private const val ARG_PERSON_ID = "personId"

        /**
         * Método estático para crear una nueva instancia de PersonDetailsFragment.
         *
         * @param personId El ID de la persona de la que se mostrarán los detalles.
         * @return Una nueva instancia de PersonDetailsFragment.
         */
        fun newInstance(personId: Int): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            val args = Bundle()

            args.putInt(ARG_PERSON_ID, personId)

            fragment.arguments = args
            return fragment
        }
    }

    /**
     * Crea y devuelve la jerarquía de vistas asociada con el fragmento.
     *
     * @param inflater El LayoutInflater que se utiliza para inflar la vista.
     * @param container El ViewGroup en el que se inflará la vista.
     * @param savedInstanceState Bundle que contiene el estado previamente guardado del fragmento, si lo hay.
     * @return La vista raíz del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    /**
     * Llamado inmediatamente después de que onCreateView() haya creado la jerarquía de vistas del fragmento.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState Bundle que contiene el estado previamente guardado del fragmento, si lo hay.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    /**
     * Configura el fragmento.
     */
    private fun setup() {
        val personId = arguments?.getInt(ARG_PERSON_ID)

        if (personId != null) {
            initPersonInfo(personId)
            initPersonMoviesRv(personId)
        }

    }

    /**
     * Inicializa la información personal de la persona y la muestra en la interfaz de usuario.
     *
     * @param personId El ID de la persona de la que se mostrarán los detalles.
     */
    private fun initPersonInfo(personId: Int) {
        val personProfilePic: ImageView = requireView().findViewById(R.id.personDetails_personProfilePic)
        val personName: TextView = requireView().findViewById(R.id.personDetails_nameTextView)
        val personDepartment: TextView = requireView().findViewById(R.id.personDetails_departmentTextView)
        val personBirthPlace: TextView = requireView().findViewById(R.id.personDetails_birthPlaceTextView)
        val personBirthDay: TextView = requireView().findViewById(R.id.personDetails_birthdayTextView)
        val personBiography: TextView = requireView().findViewById(R.id.personDetails_biography)

        personsAccess.getPersonDetails(personId) {
            if (!it.profile_path.isNullOrEmpty()) {
                Glide.with(requireView()).load(DatosConexion.TMDB_IMAGE_BASE_URL + it.profile_path).into(personProfilePic)
            } else {
                personProfilePic.setImageResource(R.drawable.logofilmosispremium)
            }

            personName.text = it.name

            when (it.gender) {
                1 -> {
                    if (it.known_for_department == "Directing") {
                        personDepartment.text = "Directora"
                    } else if (it.known_for_department == "Acting") {
                        personDepartment.text = "Actriz"
                    }
                }
                else -> {
                    if (it.known_for_department == "Directing") {
                        personDepartment.text = "Director"
                    } else if (it.known_for_department == "Acting") {
                        personDepartment.text = "Actor"
                    }
                }
            }

            personBirthPlace.text = it.place_of_birth
            personBirthDay.text = it.birthday
            personBiography.text = it.biography
        }
    }

    /**
     * Inicializa y muestra las películas en las que ha participado la persona en un RecyclerView.
     *
     * @param personId El ID de la persona cuyas películas se mostrarán.
     */
    private fun initPersonMoviesRv(personId: Int) {
        val personMoviesRv: RecyclerView = requireView().findViewById(R.id.personDetails_personMoviesRv)
        personMoviesRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val castList: ArrayList<Cast> = ArrayList()

        personsAccess.getPersonCombinedCredits(personId) {
            val sortedList = it.sortedByDescending { cast -> cast.vote_count }

            sortedList.forEach { cast ->
                castList.add(cast)
            }

            val castsAdapter = PersonMoviesCastAdapter(castList) { castClicked ->
                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, PeliculaSeleccionadaFragment.newInstance(castClicked.id))
                transaction.addToBackStack(null)
                transaction.commit()

            }

            personMoviesRv.adapter = castsAdapter
        }
    }

    // hablar con adrianixx_sniper_you_xx, estos son los datos de mi fragmento peliculaSeleccionada, los datos del director
    /**
     * Navega a la pantalla de detalles de una película cuando se hace clic en una película en el RecyclerView.
     *
     * @param movie El objeto Cast que representa la película seleccionada.
     */
    fun navigateToMovie(movie:Cast){
        val bundle = Bundle().apply {
            putInt("movieId", movie.id)
            putString("title", movie.title)
            putString("overview", movie.overview)
            putDouble("popularity", movie.popularity)
            putString("release_date", movie.release_date)
            putDouble("vote_average", movie.vote_average)
            putInt("vote_count", movie.vote_count)
            putBoolean("adult", movie.adult)
            putString("backdrop_path", movie.backdrop_path)
            putString("original_language", movie.original_language)
            putString("original_title", movie.original_title)
            putBoolean("video", movie.video)
            putString("poster_path", movie.poster_path)

        }
        val nuevoFragmento = PeliculaSeleccionadaFragment().apply {
            arguments = bundle
        }

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, nuevoFragmento)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}