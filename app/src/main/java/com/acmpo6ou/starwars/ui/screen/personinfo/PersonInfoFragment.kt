package com.acmpo6ou.starwars.ui.screen.personinfo

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
import com.acmpo6ou.starwars.model.Person
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PersonInfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private val args: PersonInfoFragmentArgs by navArgs()
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
                    val person = remember { Json.decodeFromString<Person>(args.json) }
                    PersonInfoScreen(
                        person,
                        viewModel.favoritePeopleUrls,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                        {
                            viewModel.loadFilms(it)
                            navController?.popBackStack(R.id.nav_graph, true)
                            navController?.navigate(R.id.filmListFragment)
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
