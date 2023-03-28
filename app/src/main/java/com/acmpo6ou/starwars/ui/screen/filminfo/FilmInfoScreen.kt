package com.acmpo6ou.starwars.ui.screen.filminfo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.acmpo6ou.starwars.model.Film

@Composable
fun FilmInfoScreen(film: Film) {
    Column {
        Text(
            text = film.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
    }
}
