package com.andrew.alatreon.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.andrew.alatreon.R
import com.andrew.alatreon.databinding.ActivityMainBinding
import com.andrew.alatreon.ui.fragment.PopularMoviesFragment
import com.andrew.alatreon.ui.fragment.TopRatedMoviesFragment
import com.andrew.alatreon.ui.fragment.UpcomingMoviesFragment
import com.andrew.alatreon.util.Logger

class MainActivity : AppCompatActivity(), Logger {
    //Adding the viewBinding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        enableEdgeToEdge()
        //Inflating the viewBinding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popular_movies -> replaceFragment(PopularMoviesFragment())
                R.id.top_rated_movies -> replaceFragment(TopRatedMoviesFragment())
                R.id.upcoming_movies -> replaceFragment(UpcomingMoviesFragment())
                else -> {
                    logInfo("default fragment")
                    replaceFragment(PopularMoviesFragment())
                }
            }
            true
        }
        viewModelObservers()
    }

    //Handling the fragment replacement
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

    private fun viewModelObservers(){

    }
}