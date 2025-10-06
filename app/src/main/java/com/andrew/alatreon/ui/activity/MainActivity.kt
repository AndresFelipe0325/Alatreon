package com.andrew.alatreon.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andrew.alatreon.R
import com.andrew.alatreon.databinding.ActivityMainBinding
import com.andrew.alatreon.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    //Adding the viewBinding variable
    private lateinit var binding: ActivityMainBinding
    //Attach the viewModel variable
    private val viewModel: MainActivityViewModel by viewModels()

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
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModelObservers()
    }

    private fun viewModelObservers(){

    }
}