package com.acmpo6ou.starwars

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.acmpo6ou.starwars.model.FavoritesRepo
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITES
import com.acmpo6ou.starwars.model.MainRepo
import com.google.android.material.navigation.NavigationView
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
        setContentView(R.layout.activity_main)

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
        val prefs = getSharedPreferences(FAVORITES, MODE_PRIVATE)
        val favoritesRepo = FavoritesRepo(prefs)
        viewModel.initialize(mainRepo, favoritesRepo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.popBackStack(R.id.nav_graph, true)
        navController.navigate(item.itemId)
        return true
    }
}
