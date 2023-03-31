package com.acmpo6ou.starwars.ui.screen.filmlist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_FILMS
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.ui.FavoriteButton
import com.acmpo6ou.starwars.ui.SearchField

@Composable
fun FilmListScreen(
    filmList: SnapshotStateList<Film>,
    searchText: MutableLiveData<String>,
    loading: MutableLiveData<Boolean>,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (film: Film) -> Unit,
) {
    val films = remember { filmList }
    val text by searchText.observeAsState()
    val isLoading by loading.observeAsState()

    Column {
        SearchField(searchText)
        if (isLoading == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
            }
        } else {
            LazyColumn {
                items(items = films, key = { film: Film -> film.episodeId }) {
                    if (text.toString().lowercase() in it.title.lowercase()) {
                        FilmItem(it, favorites, addFavorite, removeFavorite, navigate)
                    }
                }
            }
        }
    }
}

@Composable
fun FilmItem(
    film: Film,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (film: Film) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable { navigate(film) }
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row {
                Text(
                    text = film.title,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                FavoriteButton(
                    film.url,
                    FAVORITE_FILMS,
                    favorites,
                    addFavorite,
                    removeFavorite,
                )
            }
            Text(stringResource(R.string.release_date, film.releaseDate))
            Text(stringResource(R.string.directors, film.director))
            Text(stringResource(R.string.producers, film.producer))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun FilmItemPreview() {
    FilmItem(
        Film(
            title = "A New Hope",
            releaseDate = "1977-05-25",
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
        ),
        mutableStateListOf(),
        { _, _ -> },
        { _, _ -> },
    ) {}
}
