import android.content.Context
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.filmosis.R
import com.example.filmosis.fragments.ExploreFragment
import org.junit.Before
import org.junit.Test

class ExploreFragmentTest {

    private lateinit var scenario: FragmentScenario<ExploreFragment>

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Puedes realizar configuraciones adicionales aquí, si es necesario
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Filmosis)
    }

    @Test
    fun testSearchView() {
        Espresso.onView(ViewMatchers.withId(R.id.explore_searchView))
            .perform(ViewActions.typeText("Avengers"))
            .perform(ViewActions.pressImeActionButton()) // Simula la acción de enviar la consulta de búsqueda

        // Asegúrate de que el resultado de la búsqueda sea visible en el RecyclerView correspondiente
        Espresso.onView(ViewMatchers.withId(R.id.explore_moviesListsRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFilterButtons() {
        // Puedes agregar más pruebas para los botones de filtro aquí
        Espresso.onView(ViewMatchers.withId(R.id.explore_bestRatedBtn))
            .perform(ViewActions.click())

        // Asegúrate de que el RecyclerView de películas filtradas se actualice correctamente
        Espresso.onView(ViewMatchers.withId(R.id.explore_moviesListsRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Puedes agregar más pruebas según sea necesario para otras funcionalidades del fragmento
}
