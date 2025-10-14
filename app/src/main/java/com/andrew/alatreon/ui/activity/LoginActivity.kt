package com.andrew.alatreon.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andrew.alatreon.R
import com.andrew.alatreon.databinding.ActivityLoginBinding
import com.andrew.alatreon.util.Logger
import com.andrew.alatreon.viewmodel.LoginActivityViewModel

class LoginActivity : AppCompatActivity(), Logger {
    //Adding the viewBinding variable
    private lateinit var binding: ActivityLoginBinding
    //Attach the viewModel variable
    private val viewModel: LoginActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        enableEdgeToEdge()
        //Inflating the viewBinding variable
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login(etUser.toString(), etPassword.toString())
            }
        }
        viewModelObservers()
    }

    private fun viewModelObservers(){
        viewModel.loading.observe(this) { isLoading ->
            binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.authError.observe(this) { isAuthError ->
            binding.tvError.visibility = if(isAuthError) View.VISIBLE else View.GONE
        }

        viewModel.authSuccess.observe(this) { isAuthSuccess ->
            if(isAuthSuccess) {
                logDebug("Login successful")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}