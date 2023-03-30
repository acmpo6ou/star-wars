package com.acmpo6ou.starwars.ui.screen.starhiplist

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

class StarshipListFragment : Fragment() {
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
                    StarshipListScreen(
                        viewModel.starshipList,
                        viewModel.searchText,
                        viewModel.loading,
                        viewModel.favoriteStarshipUrls,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                    ) {
                        val json = Json.encodeToString(it)
                        val action = StarshipListFragmentDirections.actionStarshipInfo(json)
                        navController?.navigate(action)
                    }
                }
            }
        }
    }
}
