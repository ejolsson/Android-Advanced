package com.example.androidadvanced.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.MainActivity
import com.example.androidadvanced.R
import com.example.androidadvanced.databinding.LoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.w("Tag", "onCreate")

        lifecycleScope.launch {
            viewModel.loginState.collect(){
                when(it){
                    is LoginViewModel.LoginState.OnLoginReceived -> {
                        Log.w("Tag", "Login success, token = ${it.token}")
//                        HeroActivity.launch(this@LoginActivity, it.token)
//                        MainActivity.launch(this@LoginActivity, it.token)
                    }
                    is LoginViewModel.LoginState.Error -> Log.w("Tag", "Login error")
                    is LoginViewModel.LoginState.Idle -> Unit
                }
            }
        }
        val emailRapid = "ejolsson@gmail.com"//todo:Remove.Testingonly.
        val passwordRapid = "vamosRafa2023!"//todo:Remove.Testingonly.

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.bLogin)

        loginButton.setOnClickListener {
            Log.w("Tag","Login button tapped")
//            viewModel.userLogin("${email.text}","${password.text}")
//            viewModel.userLogin(emailRapid,passwordRapid)
        }
    }
}