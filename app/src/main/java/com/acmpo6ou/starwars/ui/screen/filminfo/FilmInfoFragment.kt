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
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme

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
                    val film = remember {
                        viewModel.filmsList[args.filmIndex]
                    }
                    FilmInfoScreen(
                        film, viewModel.favoriteFilms,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                        {
                            viewModel.loadCharacters(it)
                            val action = FilmInfoFragmentDirections.filmToPeople()
                            navController?.navigate(action)
                        },
                        {
                            viewModel.loadStarships(it)
                            val action = FilmInfoFragmentDirections.filmToStarships()
                            navController?.navigate(action)
                        },
                    )
                }
            }
        }
    }
}
