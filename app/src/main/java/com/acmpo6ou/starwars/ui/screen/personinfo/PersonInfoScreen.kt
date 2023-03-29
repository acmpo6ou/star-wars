package com.acmpo6ou.starwars.ui.screen.personinfo

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.Person

@Composable
fun PersonInfoScreen(
    person: Person,
    viewFilms: (urls: List<String>) -> Unit,
    viewStarships: (urls: List<String>) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = person.name,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(stringResource(R.string.birth_date, person.birthYear))
        Text(stringResource(R.string.gender, person.gender))
        Text(stringResource(R.string.skin_color, person.skinColor))
        Text(stringResource(R.string.eye_color, person.eyeColor))
        Text(stringResource(R.string.hair_color, person.hairColor))
        Text(stringResource(R.string.mass, person.mass))
        Text(stringResource(R.string.height, person.height))
        Text(stringResource(R.string.home_world, person.homeworld))

        if (person.films.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_films),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewFilms(person.films) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }

        if (person.starships.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_starships),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewStarships(person.starships) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}
