package com.example.filmosis

import com.example.filmosis.data.access.tmdb.MoviesAccess
import com.example.filmosis.data.model.tmdb.Movie
import com.example.filmosis.network.RetrofitService
import com.example.filmosis.network.interfaces.TmdbApiInterface
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MoviesAccessTest{

    @Mock
    private lateinit var mockRetrofitService: RetrofitService

    @Mock
    private lateinit var mockTmdbApi: TmdbApiInterface

    private lateinit var moviesAccess: MoviesAccess

    @Before
    fun setUp() {
        `when`(mockRetrofitService.tmdbApi).thenReturn(mockTmdbApi)
        moviesAccess = MoviesAccess()
    }

    // Comprueba si la llamada a la api funciona correctamente
    @Test
    fun listPopularMovies_success() {
        var actualMovies: List<Movie>? = null
        moviesAccess.listPopularMovies { movies ->
            actualMovies = movies
        }

        Assert.assertFalse(actualMovies.isNullOrEmpty())
    }

    // Comprueba si la llamada a la api pasando datos incorrectos devuelve null
    @Test
    fun listPopularMovies_failure() {
        val failureResponse = Response.error<List<Movie>>(404, null)
        `when`(mockTmdbApi.listPopularMovies(any(), any())).thenAnswer {
            val callback = it.arguments[1] as Callback<List<Movie>>
            callback.onResponse(mock(Call::class.java) as Call<List<Movie>>, failureResponse)
        }

        var actualMovies: List<Movie>? = null
        moviesAccess.listPopularMovies { movies ->
            actualMovies = movies
        }

        Assert.assertNull(actualMovies)
    }

}
