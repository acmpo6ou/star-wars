package com.acmpo6ou.starwars.ui.screen.starhiplist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.acmpo6ou.starwars.model.Starship

@Composable
fun StarshipListScreen(
    starshipList: SnapshotStateList<Starship>,
    navigate: (starship: Starship) -> Unit,
) {
    val starships = remember { starshipList }
    // TODO: show loading when there are no films
    LazyColumn() {
        items(items = starships, key = { starship: Starship -> starship.name }) {
            FilmItem(it, navigate)
        }
    }
}

@Composable
fun FilmItem(starship: Starship, navigate: (starship: Starship) -> Unit) {
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
            Text(
                text = starship.name,
                // TODO: why doesn't it work?
                fontWeight = FontWeight.Bold,
            )
            Text(stringResource(R.string.model, starship.model))
            Text(stringResource(R.string.hyperdrive_rating, starship.hyperdriveRating))
            Text(stringResource(R.string.max_speed, starship.maxAtmospheringSpeed))
        }
    }
}

