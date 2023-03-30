package com.acmpo6ou.starwars.ui.screen.starhiplist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.FavoritesRepo
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.ui.FavoriteButton

@Composable
fun StarshipListScreen(
    starshipList: SnapshotStateList<Starship>,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (starship: Starship) -> Unit,
) {
    val starships = remember { starshipList }
    // TODO: show loading when there are no films
    LazyColumn() {
        items(items = starships, key = { starship: Starship -> starship.name }) {
            StarshipItem(it, favorites, addFavorite, removeFavorite, navigate)
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
                    // TODO: why doesn't it work?
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                FavoriteButton(
                    starship.url, FavoritesRepo.FAVORITE_STARSHIPS,
                    favorites, addFavorite, removeFavorite,
                )
            }

            Text(stringResource(R.string.model, starship.model))
            Text(stringResource(R.string.hyperdrive_rating, starship.hyperdriveRating))
            Text(stringResource(R.string.max_speed, starship.maxAtmospheringSpeed))
        }
    }
}
