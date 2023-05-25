package com.example.androidadvanced.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.R
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
    }
    private fun setListeners() {
        val emailRapid = "ejolsson@gmail.com" // todo:Remove. Testing only.
        val passwordRapid = "vamosRafa2023!" // todo:Remove. Testing only.

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.bLogin)

        loginButton.setOnClickListener {
//            viewModel.userLogin("${email.text}","${password.text}")
//            viewModel.userLogin(emailRapid,passwordRapid)
            viewModel.userLogin4(emailRapid, passwordRapid)
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
                    is LoginViewModel.LoginState.OnLoginReceived -> {
                        Log.d("Tag LoginAct", "Login token = ${loginState.token}")
                        Log.d("Tag", "..........................")
                        HeroActivity.launch(this@LoginActivity, loginState.token)
                    }
                    is LoginViewModel.LoginState.Error -> Log.d("Tag LoginAct", "Login error")
                    is LoginViewModel.LoginState.Idle -> Unit
                }
            }
        }
    }
}