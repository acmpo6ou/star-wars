package com.acmpo6ou.starwars

import android.content.Context.MODE_PRIVATE
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITES
import org.junit.After
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @After
    fun clearStorage() {
        val prefs = compose.activity.getSharedPreferences(FAVORITES, MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    private fun navigate(id: Int) {
        val activity = compose.activity
        activity.openOptionsMenu()
        val menuItem = activity.menu?.findItem(id) ?: throw Exception()
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            activity.onOptionsItemSelected(menuItem)
        }
    }

    @Test
    fun mainTest() {
        // navigate to Favorites
        navigate(R.id.favoritesFragment)

        // there should be no favorites
        compose.onNodeWithText("Favorite films")
            .assertDoesNotExist()
        compose.onNodeWithText("Favorite people")
            .assertDoesNotExist()
        compose.onNodeWithText("Favorite starships")
            .assertDoesNotExist()

        // go back to films
        navigate(R.id.filmListFragment)

        // wait for them to load
        compose.waitUntil(10_000) {
            compose.onAllNodesWithTag("search_field")
                .fetchSemanticsNodes().size == 1
        }

        // search for "sith"
        compose.onNodeWithTag("search_field")
            .performTextInput("sith")

        compose.onNodeWithText("Revenge of the Sith")
            .assertIsDisplayed()

        // navigate to film info
        compose.onNodeWithText("Revenge of the Sith")
            .performClick()

        // the info should be correct
        compose.onNodeWithText("Revenge of the Sith")
            .assertIsDisplayed()
        compose.onNodeWithText("Released: 2005-05-19")
            .assertIsDisplayed()
        compose.onNodeWithText("Directors: George Lucas")
            .assertIsDisplayed()
        compose.onNodeWithText("Producers: Rick McCallum")
            .assertIsDisplayed()
        compose.onNodeWithText("War! The Republic", substring = true)
            .assertIsDisplayed()

        // add film to favorites
        compose.onNodeWithContentDescription("Add Revenge of the Sith to favorites")
            .performClick()

        // view the characters
        compose.onNodeWithText("VIEW CHARACTERS")
            .performClick()

        // search field should be empty, so it should contain the placeholder text "Search"
        compose.onNodeWithTag("search_field")
            .assertTextEquals("Search", "")

        compose.onRoot().printToLog("mylog")

        // wait for people to get loaded
        compose.waitUntil(10_000) {
            compose.onAllNodesWithContentDescription("Add Luke Skywalker to favorites")
                .fetchSemanticsNodes().size == 1
        }

        // favorite Luke Skywalker
        compose.onNodeWithContentDescription("Add Luke Skywalker to favorites")
            .performClick()

        // select Luke Skywalker
        compose.onNodeWithText("Luke Skywalker")
            .performClick()

        // the info should be correct
        compose.onNodeWithText("Luke Skywalker")
            .assertIsDisplayed()
        compose.onNodeWithText("Birth year: 19BBY")
            .assertIsDisplayed()
        compose.onNodeWithText("Gender: male")
            .assertIsDisplayed()
        compose.onNodeWithText("Skin color: fair")
            .assertIsDisplayed()
        compose.onNodeWithText("Eye color: blue")
            .assertIsDisplayed()
        compose.onNodeWithText("Hair color: blond")
            .assertIsDisplayed()
        compose.onNodeWithText("Mass: 77")
            .assertIsDisplayed()
        compose.onNodeWithText("Height: 172")
            .assertIsDisplayed()

        // he should be in favorites, so there should be a button to remove him from favorites
        compose.onNodeWithContentDescription("Remove Luke Skywalker from favorites")
            .assertIsDisplayed()

        // view the starships
        compose.onNodeWithText("VIEW STARSHIPS")
            .performClick()

        // wait for them to load
        compose.waitUntil(10_000) {
            compose.onAllNodesWithText("X-wing")
                .fetchSemanticsNodes().size == 1
        }

        // there should be "X-wing" and "Imperial shuttle"
        compose.onNodeWithText("X-wing")
            .assertIsDisplayed()
        compose.onNodeWithText("Imperial shuttle")
            .assertIsDisplayed()

        // favorite Imperial shuttle
        compose.onNodeWithContentDescription("Add Imperial shuttle to favorites")
            .performClick()

        // select X-wing
        compose.onNodeWithText("X-wing")
            .performClick()

        // the info should be correct
        compose.onNodeWithText("X-wing")
            .assertIsDisplayed()
        compose.onNodeWithText("Model: T-65 X-wing")
            .assertIsDisplayed()
        compose.onNodeWithText("Hyperdrive rating: 1.0")
            .assertIsDisplayed()
        compose.onNodeWithText("Max speed: 1050")
            .assertIsDisplayed()
        compose.onNodeWithText("Manufacturer: Incom Corporation")
            .assertIsDisplayed()
        compose.onNodeWithText("Class: Starfighter")
            .assertIsDisplayed()
        compose.onNodeWithText("Length: 12.5")
            .assertIsDisplayed()
        compose.onNodeWithText("Crew: 1")
            .assertIsDisplayed()
        compose.onNodeWithText("Passengers: 0")
            .assertIsDisplayed()
        compose.onNodeWithText("Cargo Capacity: 110")
            .assertIsDisplayed()
        compose.onNodeWithText("Consumables: 1 week")
            .assertIsDisplayed()
        compose.onNodeWithText("MGLT: 100")
            .assertIsDisplayed()
        compose.onNodeWithText("Cost in Credits: 149999")
            .assertIsDisplayed()

        // it shouldn't be in favorites, so there should be a button to add it
        compose.onNodeWithContentDescription("Add X-wing to favorites")
            .assertIsDisplayed()

        // view the films
        compose.onNodeWithText("VIEW FILMS")
            .performClick()

        // wait for them to load
        compose.waitUntil(10_000) {
            compose.onAllNodesWithText("A New Hope")
                .fetchSemanticsNodes().size == 1
        }

        // there should be 3 films
        compose.onNodeWithText("A New Hope")
            .assertIsDisplayed()
        compose.onNodeWithText("The Empire Strikes Back")
            .assertIsDisplayed()
        compose.onNodeWithText("Return of the Jedi")
            .assertIsDisplayed()

        // go to favorites
        navigate(R.id.favoritesFragment)

        // wait for them to load
        compose.waitUntil(10_000) {
            compose.onAllNodesWithText("Imperial shuttle")
                .fetchSemanticsNodes().size == 1
        }

        // there should be some favorites
        compose.onNodeWithText("Favorite films")
            .assertExists()
        compose.onNodeWithText("Favorite people")
            .assertExists()
        compose.onNodeWithText("Favorite starships")
            .assertExists()
        compose.onNodeWithText("Revenge of the Sith")
            .assertExists()
        compose.onNodeWithText("Luke Skywalker")
            .assertExists()
        compose.onNodeWithText("Imperial shuttle")
            .assertExists()
    }
}
