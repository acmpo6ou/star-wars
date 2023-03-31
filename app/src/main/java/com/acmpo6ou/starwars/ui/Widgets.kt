package com.acmpo6ou.starwars.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
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

@Composable
fun SearchField(
    searchText: MutableLiveData<String>,
) {
    val text by searchText.observeAsState()
    OutlinedTextField(
        value = text ?: "",
        onValueChange = { value: String ->
            searchText.value = value
        },
        label = { Text(stringResource(R.string.search)) },
        singleLine = true,
        modifier = Modifier
            .testTag("search_field")
            .padding(8.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(onClick = { searchText.value = "" }) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear),
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            cursorColor = MaterialTheme.colors.onBackground,
        ),
    )
}
