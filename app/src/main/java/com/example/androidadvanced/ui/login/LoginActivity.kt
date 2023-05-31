package com.example.androidadvanced.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.R
import com.example.androidadvanced.data.User
import com.example.androidadvanced.databinding.LoginBinding
import com.example.androidadvanced.ui.home.HeroActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        setListeners()
        if (!User.getToken(this).isNullOrBlank()) {
            Log.w("Tag login", "Token onhand, skip login...")
            goToHeroActivity()
        }
    }
    private fun goToHeroActivity() {
        HeroActivity.start(this@LoginActivity)
        finish()
    }
    private fun setListeners() {

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.bLogin)

        loginButton.setOnClickListener {
            viewModel.userLogin4("${email.text}","${password.text}")
//            viewModel.skipLogin()
        }
    }
    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.loginState.collect(){ loginState ->
                when(loginState){
                    is LoginViewModel.LoginState.LoginRequested -> {
                        Log.d("Tag LoginAct", "is LoginViewModel.LoginState.LoginRequested")
                    }
                    is LoginViewModel.LoginState.LoginSuccess -> {
                        Log.d("Tag LoginAct", "Login token = ${loginState.token}") // token available here, prints out
                        User.updateToken(loginState.token, this@LoginActivity)
                        HeroActivity.launch(this@LoginActivity, loginState.token) // navs to HeroList
                    }
                    is LoginViewModel.LoginState.Error -> Log.d("Tag LoginAct", "Login error")
                    is LoginViewModel.LoginState.Idle -> Unit
                }
            }
        }
    }
}