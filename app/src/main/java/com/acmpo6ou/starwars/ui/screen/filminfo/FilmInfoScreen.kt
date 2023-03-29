package com.acmpo6ou.starwars.ui.screen.filminfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Film

@Composable
fun FilmInfoScreen(
    film: Film,
    viewCharacters: (urls: List<String>) -> Unit,
    viewStarships: (urls: List<String>) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = film.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(stringResource(R.string.release_date, film.releaseDate))
        Text(stringResource(R.string.directors, film.director))
        Text(stringResource(R.string.producers, film.producer))
        Text(
            text = "\n${film.openingCrawl}\n",
            fontSize = 18.sp,
        )

        if (film.characters.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_characters),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewCharacters(film.characters) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }

        if (film.starships.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_starships),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewStarships(film.starships) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}

@Composable
@Preview
fun FilmInfoPreview() {
    FilmInfoScreen(
        Film(
            title = "A New Hope",
            releaseDate = "1977-05-25",
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            openingCrawl =
            """
            It is a period of civil war.\n\n
            Rebel spaceships, striking\n\n
            from a hidden base, have won\n\n
            their first victory against\n\n
            the evil Galactic Empire.\n\n\n\n
            During the battle, Rebel\n\n
            spies managed to steal secret\r\n
            plans to the Empire's\n\n
            ultimate weapon, the DEATH\n\n
            STAR, an armored space\n\n
            station with enough power\n\n
            to destroy an entire planet.\n\n\n\n
            Pursued by the Empire's\n\n
            sinister agents, Princess\n\n
            Leia races home aboard her\n\n
            starship, custodian of the\n\n
            stolen plans that can save her\n\n
            people and restore\n\n
            freedom to the galaxy....
            """.trimIndent(),
        ),
        {},
    ) {}
}
