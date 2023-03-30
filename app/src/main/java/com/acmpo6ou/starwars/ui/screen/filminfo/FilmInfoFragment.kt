package com.acmpo6ou.starwars.ui.screen.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FilmInfoFragment : Fragment() {
    // TODO: extract to a superclass
    private val viewModel: MainViewModel by activityViewModels()
    private val args: FilmInfoFragmentArgs by navArgs()
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
                    val film = remember { Json.decodeFromString<Film>(args.json) }
                    FilmInfoScreen(
                        film,
                        viewModel.favoriteFilmUrls,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                        {
                            viewModel.loadPeople(it)
                            navController?.popBackStack(R.id.nav_graph, true)
                            navController?.navigate(R.id.peopleListFragment)
                        },
                        {
                            viewModel.loadStarships(it)
                            navController?.popBackStack(R.id.nav_graph, true)
                            navController?.navigate(R.id.starshipListFragment)
                        },
                    )
                }
            }
        }
    }
}
