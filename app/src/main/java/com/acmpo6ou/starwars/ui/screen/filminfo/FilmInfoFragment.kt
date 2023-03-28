package com.acmpo6ou.starwars.ui.screen.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.acmpo6ou.starwars.MainActivity
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.client.PeopleClient
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

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
                    FilmInfoScreen(film, {
                        loadCharacters(it)
                        val action = FilmInfoFragmentDirections.actionPeopleList()
                        navController?.navigate(action)
                    }) {}
                }
            }
        }
    }

    private fun loadCharacters(urls: List<String>) {
        viewModel.viewModelScope.launch(Dispatchers.Default) {
            val service = (activity as? MainActivity)
                ?.retrofit
                ?.create(PeopleClient::class.java)
            viewModel.peopleList.clear()
            for (url in urls) {
                service?.getPerson(url)
                    ?.awaitResponse()
                    ?.body()
                    ?.let {
                        viewModel.viewModelScope.launch(Dispatchers.Main) {
                            viewModel.peopleList.add(it)
                        }
                    }
            }
        }
    }
}
