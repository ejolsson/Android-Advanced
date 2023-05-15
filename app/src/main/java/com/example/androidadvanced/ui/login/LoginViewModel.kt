package com.example.androidadvanced.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun userLogin(email: String, password: String) {
    Log.w("Tag LoginVM","fun userLogin started...")
        // TODO: Refactor to use retrofit
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val baseUrl = "https://dragonball.keepcoding.education/api/"
            val url = "${baseUrl}auth/login"
            val credentials = Credentials.basic(email, password)
            val formBody = FormBody.Builder().build()
            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization",credentials)
                .post(formBody)
                .build()
            val call = client.newCall(request)
            val response = call.execute()

            response.body?.let { responseBody ->
                val tokenPublic = responseBody.string()
                Log.w("Tag LoginVM","Login successful. tokenPublic = $tokenPublic")
                _loginState.value= LoginState.OnLoginReceived(tokenPublic)
            } ?: run { _loginState.value = LoginState.Error("Something went wrong in the request") }
        }
    }

    sealed class LoginState {
        object Idle: LoginState()
        data class Error(val error: String): LoginState()
        data class OnLoginReceived(val token: String): LoginState()
    }
}














