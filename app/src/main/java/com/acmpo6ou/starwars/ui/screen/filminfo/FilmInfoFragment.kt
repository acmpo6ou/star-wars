package com.acmpo6ou.starwars.ui.screen.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme

class FilmInfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private val args: FilmInfoFragmentArgs by navArgs()

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
                    FilmInfoScreen(film)
                }
            }
        }
    }
}
