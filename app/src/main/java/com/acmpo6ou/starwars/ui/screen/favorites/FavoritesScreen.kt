package com.acmpo6ou.starwars.ui.screen.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.acmpo6ou.starwars.ui.SearchField
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
    val text by viewModel.searchText.observeAsState()
    val isLoading by viewModel.loading.observeAsState()
    val films = viewModel.favoriteFilms.filter {
        text.toString().lowercase() in it.title.lowercase()
    }
    val people = viewModel.favoritePeople.filter {
        text.toString().lowercase() in it.name.lowercase()
    }
    val starships = viewModel.favoriteStarships.filter {
        text.toString().lowercase() in it.name.lowercase()
    }

    Column {
        SearchField(viewModel.searchText)
        if (films.size + people.size + starships.size == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.no_items),
                    fontSize = 20.sp,
                )
            }
        }
        if (isLoading == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
            }
            return@Column
        }
        LazyColumn {
            if (films.isNotEmpty()) {
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

            if (people.isNotEmpty()) {
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

            if (starships.isNotEmpty()) {
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
}
