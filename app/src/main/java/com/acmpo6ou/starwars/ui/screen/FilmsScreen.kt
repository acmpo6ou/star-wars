package com.acmpo6ou.starwars.ui.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Film

@Composable
fun FilmsScreen(filmList: SnapshotStateList<Film>) {
    val films = remember { filmList }
    LazyColumn() {
        items(items = films, key = { film: Film -> film.episodeId }) {
            FilmItem(it)
        }
    }
}

@Composable
fun FilmItem(film: Film) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable {}
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Column {
            Text(
                text = film.title,
                fontWeight = FontWeight.Bold,
            )
            Text(stringResource(R.string.release_date, film.releaseDate))
            Text(stringResource(R.string.directors, film.director))
            Text(stringResource(R.string.producers, film.producer))
        }
    }
}

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
    )
}
