package com.acmpo6ou.starwars.ui.screen.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavoritesFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private val navController: NavController?
        get() {
            val navHostFragment = activity?.supportFragmentManager
                ?.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            return navHostFragment?.navController
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                StarWarsTheme {
                    // TODO: ensure it's always loaded correctly
                    viewModel.loadFavorites()
                    FavoritesScreen(
                        viewModel,
                        {
                            val json = Json.encodeToString(it)
                            val action = FavoritesFragmentDirections.favToFilm(json)
                            navController?.navigate(action)
                        },
                        {
                            val json = Json.encodeToString(it)
                            val action = FavoritesFragmentDirections.favToPerson(json)
                            navController?.navigate(action)
                        },
                        {
                            val json = Json.encodeToString(it)
                            val action = FavoritesFragmentDirections.favToStarship(json)
                            navController?.navigate(action)
                        },
                    )
                }
            }
        }
    }
}
