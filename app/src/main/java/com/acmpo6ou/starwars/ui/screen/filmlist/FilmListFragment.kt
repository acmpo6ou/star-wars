package com.acmpo6ou.starwars.ui.screen.filmlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme

class FilmListFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                StarWarsTheme {
                    FilmListScreen(viewModel.filmsList)
                }
            }
        }
    }
}
