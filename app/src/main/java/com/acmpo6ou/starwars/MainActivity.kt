package com.acmpo6ou.starwars

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.acmpo6ou.starwars.model.Film

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filmsList = listOf(
            Film(
                title = "A New Hope",
                releaseDate = "1977-05-25",
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
            ),
        )
        viewModel.initialize(filmsList)
        setContentView(R.layout.activity_main)
    }
}
