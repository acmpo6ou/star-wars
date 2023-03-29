package com.acmpo6ou.starwars.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.acmpo6ou.starwars.R

@Composable
fun FavoriteButton(
    item: String,
    key: String,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
) {
    if (item in favorites) {
        IconButton(onClick = { removeFavorite(key, item) }) {
            Icon(
                painterResource(R.drawable.favorite),
                stringResource(R.string.remove_favorite, item),
            )
        }
    } else {
        IconButton(onClick = { addFavorite(key, item) }) {
            Icon(
                painterResource(R.drawable.favorite_border),
                stringResource(R.string.add_favorite, item),
            )
        }
    }
}
