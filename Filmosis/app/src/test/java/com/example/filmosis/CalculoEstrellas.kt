package com.example.filmosis

import android.content.Context
import android.widget.RatingBar
import com.example.filmosis.fragments.PeliculaSeleccionadaFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CalculoEstrellasTest {

    private lateinit var fragment: PeliculaSeleccionadaFragment
    private val maxRating = 10
    private val voteAverageZero = 0.0
    private val voteAverageMax = maxRating.toDouble()

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = PeliculaSeleccionadaFragment()
        `when`(mockContext.applicationContext).thenReturn(mockContext)
        fragment.tvAvg = RatingBar(mockContext)
        fragment.tvAvg.numStars = maxRating
    }

    @Test
    fun testCalculoEstrellas_devuelve_la_calificación_esperada() {
        val voteAverage = 7.5
        val expectedRating = calcularRating(voteAverage)
        fragment.tvAvg.rating = expectedRating.toFloat()
        assertThat(fragment.tvAvg.rating).isEqualTo(expectedRating)
    }

    @Test
    fun testCalculoEstrellas_puntaje_cero() {
        val expectedRating = calcularRating(voteAverageZero)
        fragment.tvAvg.rating = expectedRating.toFloat()
        assertThat(fragment.tvAvg.rating).isEqualTo(0.0f)
    }

    @Test
    fun testCalculoEstrellas_puntaje_maximo() {
        val expectedRating = calcularRating(voteAverageMax)
        fragment.tvAvg.rating = expectedRating.toFloat()
        assertThat(fragment.tvAvg.rating).isEqualTo(fragment.tvAvg.numStars)
    }

    // Agrega más pruebas similares para otros casos

    private fun calcularRating(voteAverage: Double): Float {
        return ((voteAverage / maxRating) * fragment.tvAvg.numStars).toFloat()
    }
}
