package com.acmpo6ou.starwars

import android.os.Bundle
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
import com.acmpo6ou.starwars.model.MainRepo
import com.google.android.material.navigation.NavigationView
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private val viewModel: MainViewModel by viewModels()
    lateinit var retrofit: Retrofit

    @Suppress("JSON_FORMAT_REDUNDANT")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // setup navigation controller
        drawerLayout = findViewById(R.id.drawer_layout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // setup navigation view
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        drawerLayout.closeDrawers()
        return item.onNavDestinationSelected(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
