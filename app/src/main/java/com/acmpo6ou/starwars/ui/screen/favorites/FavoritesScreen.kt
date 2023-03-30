package com.acmpo6ou.starwars.ui.screen.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.model.Person
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.ui.screen.filmlist.FilmItem
import com.acmpo6ou.starwars.ui.screen.peoplelist.PersonItem
import com.acmpo6ou.starwars.ui.screen.starhiplist.StarshipItem

@Composable
fun FavoritesScreen(viewModel: MainViewModel) {
    // TODO: show loading when there are no films
    val films = remember { viewModel.favoriteFilms }
    val people = remember { viewModel.favoritePeople }
    val starships = remember { viewModel.favoriteStarships }
    LazyColumn {
        if (films.size > 0) {
            item { Text(stringResource(R.string.favorite_films)) }
        }
        items(items = films, key = { film: Film -> film.episodeId }) {
            FilmItem(
                it,
                viewModel.favoriteFilmUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
            ) {}
        }

        if (people.size > 0) {
            item { Text(stringResource(R.string.favorite_people)) }
        }
        items(items = people, key = { person: Person -> person.name }) {
            PersonItem(
                it,
                viewModel.favoritePeopleUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
            ) {}
        }

        if (starships.size > 0) {
            item { Text(stringResource(R.string.favorite_starships)) }
        }
        items(items = starships, key = { starship: Starship -> starship.name }) {
            StarshipItem(
                it,
                viewModel.favoriteStarshipUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
            ) {}
        }
    }
}
