package com.acmpo6ou.starwars.ui.screen.peoplelist

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
import com.acmpo6ou.starwars.model.Person

@Composable
fun PeopleListScreen(
    peopleList: SnapshotStateList<Person>,
    navigate: (person: Person) -> Unit,
) {
    val people = remember { peopleList }
    // TODO: show loading when there are no films
    LazyColumn() {
        items(items = people, key = { person: Person -> person.name }) {
            PersonItem(it, navigate)
        }
    }
}

@Composable
fun PersonItem(person: Person, navigate: (person: Person) -> Unit) {
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
            Text(
                text = person.name,
                // TODO: why doesn't it work?
                fontWeight = FontWeight.Bold,
            )
            Text(stringResource(R.string.birth_date, person.birthYear))
            Text(stringResource(R.string.gender, person.gender))
            Text(stringResource(R.string.skin_color, person.skinColor))
        }
    }
}
