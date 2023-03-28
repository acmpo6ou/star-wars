package com.acmpo6ou.starwars

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.starwars.client.FilmsClient
import com.acmpo6ou.starwars.model.Film
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var retrofit: Retrofit

    @Suppress("JSON_FORMAT_REDUNDANT")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentType = "application/json".toMediaType()
        retrofit = Retrofit.Builder()
            .baseUrl(ApiEndpoints.BASE_URL)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory(contentType),
            )
            .build()
        loadFilms()
        setContentView(R.layout.activity_main)
    }

    private fun loadFilms() {
        viewModel.viewModelScope.launch(Dispatchers.Default) {
            val service = retrofit.create(FilmsClient::class.java)
            val filmsList = service.getFilms()
                .awaitResponse()
                .body()
                ?.results
                ?: listOf()
            launch(Dispatchers.Main) {
                viewModel.initialize(filmsList)
            }
        }
    }
}
