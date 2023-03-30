package com.acmpo6ou.starwars.ui.screen.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
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
    val films = remember { viewModel.favoriteFilms }
    val people = remember { viewModel.favoritePeople }
    val starships = remember { viewModel.favoriteStarships }
    val text = viewModel.searchText.observeAsState()
    val isLoading by viewModel.loading.observeAsState()
    Column {
        SearchField(viewModel.searchText)
        if (isLoading == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
            return@Column
        }
        LazyColumn {
            if (films.size > 0 && text.value.isNullOrEmpty()) {
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
                if (text.value.toString().lowercase() in it.title.lowercase()) {
                    FilmItem(
                        it,
                        viewModel.favoriteFilmUrls,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                        navigateFilm,
                    )
                }
            }

            if (people.size > 0 && text.value.isNullOrEmpty()) {
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
                if (text.value.toString().lowercase() in it.name.lowercase()) {
                    PersonItem(
                        it,
                        viewModel.favoritePeopleUrls,
                        viewModel::addFavorite,
                        viewModel::removeFavorite,
                        navigatePerson,
                    )
                }
            }

            if (starships.size > 0 && text.value.isNullOrEmpty()) {
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
                if (text.value.toString().lowercase() in it.name.lowercase()) {
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
}
