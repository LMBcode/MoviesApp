package com.example.moviesapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.moviesapp.Adapter.MovieAdapters.RetrofitAdapter
import com.example.moviesapp.Fragments.*
import com.example.moviesapp.Retrofit.Movie
import com.example.moviesapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var adapter : RetrofitAdapter
    private lateinit var _binding : ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = RetrofitAdapter(mutableListOf()){movie -> getArgs(movie)}
        val topAppBar : androidx.appcompat.widget.Toolbar = findViewById(R.id.topAppBar)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        setSupportActionBar(topAppBar)
        setTransparentStatusBar()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navigationView.menu,drawerLayout)
        navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
        navController.navigateUp(appBarConfiguration)
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun setTransparentStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onSupportNavigateUp(): Boolean {
        return (this::navController.isInitialized &&
                navController.navigateUp(appBarConfiguration))
                || super.onSupportNavigateUp()
    }
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
    private fun getArgs(movie : Movie){
        val args = Bundle()
        args.putSerializable(MOVIE_BACKDROP,movie.backdropPath)
        args.putSerializable(MOVIE_TITLE,movie.title)
        args.putSerializable(MOVIE_NAME,movie.name)
        args.putSerializable(MOVIE_OVERVIEW,movie.overview)
        args.putSerializable(MOVIE_RATING, movie.rating)
        args.putSerializable(MOVIE_POSTER,movie.posterPath)
        args.putSerializable(MOVIE_RELEASE_DATE,movie.releaseDate)
        args.putSerializable(TV_DATE,movie.airDate)

    }

}