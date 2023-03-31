package com.acmpo6ou.starwars.ui.screen.peoplelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.acmpo6ou.starwars.R
import com.acmpo6ou.starwars.model.FavoritesRepo
import com.acmpo6ou.starwars.model.Person
import com.acmpo6ou.starwars.ui.FavoriteButton
import com.acmpo6ou.starwars.ui.SearchField

@Composable
fun PeopleListScreen(
    peopleList: SnapshotStateList<Person>,
    searchText: MutableLiveData<String>,
    loading: MutableLiveData<Boolean>,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (person: Person) -> Unit,
) {
    val people = remember { peopleList }
    val text = searchText.observeAsState()
    val isLoading = loading.observeAsState()
    Column {
        SearchField(searchText)
        if (isLoading.value == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
            }
        } else {
            LazyColumn {
                items(items = people, key = { person: Person -> person.name }) {
                    if (text.value.toString().lowercase() in it.name.lowercase()) {
                        PersonItem(it, favorites, addFavorite, removeFavorite, navigate)
                    }
                }
            }
        }
    }
}

@Composable
fun PersonItem(
    person: Person,
    favorites: SnapshotStateList<String>,
    addFavorite: (key: String, title: String) -> Unit,
    removeFavorite: (key: String, title: String) -> Unit,
    navigate: (person: Person) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable { navigate(person) }
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row {
                Text(
                    text = person.name,
                    // TODO: why doesn't it work?
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                FavoriteButton(
                    person.url, FavoritesRepo.FAVORITE_PEOPLE,
                    favorites, addFavorite, removeFavorite,
                )
            }

            Text(stringResource(R.string.birth_year, person.birthYear))
            Text(stringResource(R.string.gender, person.gender))
            Text(stringResource(R.string.skin_color, person.skinColor))
        }
    }
}
