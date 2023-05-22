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
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.home.herolist.HeroListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class LoginActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()
    private lateinit var binding : LoginBinding
    @OptIn(DelicateCoroutinesApi::class)
    private var scope = CoroutineScope(newSingleThreadContext("login"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Tag LoginAct", "onCreate")

        lifecycleScope.launch {
            viewModel.loginState.collect(){
                when(it){
                    is LoginViewModel.LoginState.LoginRequested -> {
                        Log.d("Tag LoginAct", "is LoginViewModel.LoginState.LoginRequested")
                    }
                    is LoginViewModel.LoginState.OnLoginReceived -> {
                        Log.d("Tag LoginAct", "Login token = ${it.token}")
                        Log.d("Tag", "..........................")
                        HeroActivity.launch(this@LoginActivity, it.token) // moving fm HeroAct to HeroListFrag
//                        HeroListFragment.launch(this@LoginActivity, it.token) // used to nav to HeroActivity & pass token, defined in class HeroListFragment
//                        MainActivity.launch(this@LoginActivity, it.token)
                    }
                    is LoginViewModel.LoginState.Error -> Log.d("Tag LoginAct", "Login error")
                    is LoginViewModel.LoginState.Idle -> Unit
                }
            }
        }

        val emailRapid = "ejolsson@gmail.com" // todo:Remove. Testing only.
        val passwordRapid = "vamosRafa2023!" // todo:Remove. Testing only.

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.bLogin)

        loginButton.setOnClickListener {
            Log.d("Tag LoginAct","Login button tapped")
//            viewModel.userLogin("${email.text}","${password.text}") // v1
            viewModel.userLogin(emailRapid,passwordRapid) // v1 rapid
//            viewModel.userLogin4(emailRapid, passwordRapid) // v3 crash
        }
    }
}