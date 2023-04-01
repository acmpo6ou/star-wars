package com.acmpo6ou.starwars.ui.screen.starhiplist

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.FavoritesRepo
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.ui.FavoriteButton
import com.acmpo6ou.starwars.ui.SearchField

@Composable
fun StarshipListScreen(
    starshipList: SnapshotStateList<Starship>,
    searchText: MutableLiveData<String>,
    loading: MutableLiveData<Boolean>,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (starship: Starship) -> Unit,
) {
    val text by searchText.observeAsState()
    val isLoading by loading.observeAsState()
    val starships = starshipList.filter { text.toString().lowercase() in it.name.lowercase() }

    Column {
        SearchField(searchText)
        if (starships.isEmpty()) {
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
        } else {
            LazyColumn {
                items(items = starships, key = { starship: Starship -> starship.name }) {
                    StarshipItem(it, favorites, addFavorite, removeFavorite, navigate)
                }
            }
        }
    }
}

@Composable
fun StarshipItem(
    starship: Starship,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (starship: Starship) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable { navigate(starship) }
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row {
                Text(
                    text = starship.name,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                FavoriteButton(
                    starship.name, starship.url, FavoritesRepo.FAVORITE_STARSHIPS,
                    favorites, addFavorite, removeFavorite,
                )
            }

            Text(stringResource(R.string.model, starship.model))
            Text(stringResource(R.string.hyperdrive_rating, starship.hyperdriveRating))
            Text(stringResource(R.string.max_speed, starship.maxAtmospheringSpeed))
        }
    }
}
