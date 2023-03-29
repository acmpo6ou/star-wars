package com.acmpo6ou.starwars.ui.screen.starshipinfo

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
import com.acmpo6ou.starwars.model.Starship

@Composable
fun StarshipInfoScreen(
    starship: Starship,
    viewPilots: (urls: List<String>) -> Unit,
    viewFilms: (urls: List<String>) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = starship.name,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(stringResource(R.string.model, starship.model))
        Text(stringResource(R.string.hyperdrive_rating, starship.hyperdriveRating))
        Text(stringResource(R.string.max_speed, starship.maxAtmospheringSpeed))
        Text(stringResource(R.string.manufacturer, starship.manufacturer))
        Text(stringResource(R.string.starship_class, starship.starshipClass))
        Text(stringResource(R.string.length, starship.length))
        Text(stringResource(R.string.crew, starship.crew))
        Text(stringResource(R.string.passengers, starship.passengers))
        Text(stringResource(R.string.cargo_capacity, starship.cargoCapacity))
        Text(stringResource(R.string.consumables, starship.consumables))
        Text(stringResource(R.string.mglt, starship.MGLT))
        Text(stringResource(R.string.cost_in_cedits, starship.costInCredits))

        if (starship.pilots.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_pilots),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewPilots(starship.pilots) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }

        if (starship.films.isNotEmpty()) {
            Text(
                text = stringResource(R.string.view_films),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { viewFilms(starship.films) }
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}
