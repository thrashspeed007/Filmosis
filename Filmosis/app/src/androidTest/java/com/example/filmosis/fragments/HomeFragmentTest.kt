package com.example.filmosis.fragments
import com.example.filmosis.R

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.filmosis.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {



    @Test
    fun test_Verificar_Existencia_de_RecyclerView_Popular() {
        // Lanza el escenario de la actividad que contiene el fragmento HomeFragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Comprueba si el RecyclerView para películas populares está presente
        onView(withId(R.id.moviesRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_Verificar_Existencia_de_RecyclerView_Proximamente() {
        // Lanza el escenario de la actividad que contiene el fragmento HomeFragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Comprueba si el RecyclerView para películas próximamente está presente
        onView(withId(R.id.moviesSoonRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_Verificar_Existencia_de_RecyclerView_Recomendadas() {
        // Lanza el escenario de la actividad que contiene el fragmento HomeFragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Comprueba si el RecyclerView para películas recomendadas está presente
        onView(withId(R.id.movieRecomendedRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_Verificar_Existencia_de_RecyclerView_Servicios() {
        // Lanza el escenario de la actividad que contiene el fragmento HomeFragment
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Comprueba si el RecyclerView para servicios está presente
        onView(withId(R.id.serviciosRecyclerview)).check(matches(isDisplayed()))
    }
}
