package com.example.filmosis.fragments

import com.example.filmosis.fragments.PeliculaSeleccionadaFragment
import com.example.filmosis.R
import android.view.View
import android.widget.RatingBar
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.data.model.tmdb.Idioma
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Tets que comprueba el fragmento se inicializa correctamente el ratingbar
 * con el rating esperado basado en el promedio de votos de la pleicula. Para esto este test
 * **/

class PeliculaSeleccionadaFragmentTest {

    @Mock
    private lateinit var viewMock: View

    @Mock
    private lateinit var tvAvgMock: RatingBar

    private lateinit var fragment: PeliculaSeleccionadaFragment

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = PeliculaSeleccionadaFragment()

        // Configurar el mock para devolver el RatingBar mock cuando se busque por ID
        `when`(viewMock.findViewById<RatingBar>(R.id.averageVote)).thenReturn(tvAvgMock)
    }

    @Test
    fun testRatingBarInitialization() {
        // Configurar los datos simulados
        val recuperacionInfo = Movie(
            adult = false,
            backdrop_path = "",
            genre_ids = emptyList(),
            id = 0,
            original_language = "",
            original_title = "",
            overview = "",
            popularity = 0.0,
            poster_path = "",
            release_date = "",
            title = "",
            video = false,
            vote_average = 7.5,
            vote_count = 0,
            spoken_languages = listOf()
        )

        fragment.recuperacionInfo = recuperacionInfo

        // Configurar el máximo rating
        val maxRating = 10

        // Calcular el rating esperado
        val expectedRating = (recuperacionInfo.vote_average.toFloat() / maxRating) * tvAvgMock.numStars

        // Verificar si el rating del mock ha sido configurado correctamente
        assertEquals(expectedRating, tvAvgMock.rating, 0.001f)
    }
}
