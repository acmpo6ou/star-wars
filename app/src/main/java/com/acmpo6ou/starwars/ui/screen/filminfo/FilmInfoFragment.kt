package com.acmpo6ou.starwars.ui.screen.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.ui.theme.StarWarsTheme
import kotlin.properties.Delegates

class FilmInfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: get index
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                StarWarsTheme {
                    val film = remember { viewModel.filmsList[index] }
                    FilmInfoScreen(film)
                }
            }
        }
    }
}
