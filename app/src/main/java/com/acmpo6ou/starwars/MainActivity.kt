package com.acmpo6ou.starwars

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.acmpo6ou.starwars.model.FavoritesRepo
import com.acmpo6ou.starwars.model.MainRepo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

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
        val mainRepo = MainRepo(retrofit)
        val prefs = getSharedPreferences("favorites", MODE_PRIVATE)
        val favoritesRepo = FavoritesRepo(prefs)
        viewModel.initialize(mainRepo, favoritesRepo)
        setContentView(R.layout.activity_main)
    }
}
