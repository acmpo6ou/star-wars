package com.acmpo6ou.starwars.ui.screen.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acmpo6ou.starwars.MainViewModel
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.model.Person
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.ui.screen.filmlist.FilmItem
import com.acmpo6ou.starwars.ui.screen.peoplelist.PersonItem
import com.acmpo6ou.starwars.ui.screen.starhiplist.StarshipItem

@Composable
fun FavoritesScreen(
    viewModel: MainViewModel,
    navigateFilm: (film: Film) -> Unit,
    navigatePerson: (person: Person) -> Unit,
    navigateStarship: (starship: Starship) -> Unit,
) {
    // TODO: show loading when there are no films
    val films = remember { viewModel.favoriteFilms }
    val people = remember { viewModel.favoritePeople }
    val starships = remember { viewModel.favoriteStarships }
    LazyColumn {
        if (films.size > 0) {
            item {
                Text(
                    text = stringResource(R.string.favorite_films),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
        items(items = films, key = { film: Film -> film.episodeId }) {
            FilmItem(
                it,
                viewModel.favoriteFilmUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
                navigateFilm,
            )
        }

        if (people.size > 0) {
            item {
                Text(
                    text = stringResource(R.string.favorite_people),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
        items(items = people, key = { person: Person -> person.name }) {
            PersonItem(
                it,
                viewModel.favoritePeopleUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
                navigatePerson,
            )
        }

        if (starships.size > 0) {
            item {
                Text(
                    text = stringResource(R.string.favorite_starships),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
        items(items = starships, key = { starship: Starship -> starship.name }) {
            StarshipItem(
                it,
                viewModel.favoriteStarshipUrls,
                viewModel::addFavorite,
                viewModel::removeFavorite,
                navigateStarship,
            )
        }
    }
}
