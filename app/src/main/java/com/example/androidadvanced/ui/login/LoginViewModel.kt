package com.example.androidadvanced.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    private val repository = LoginRepository()
    private var token: String = ""

    // v1 login
    fun userLogin(email: String, password: String) {
    Log.d("Tag LoginVM","fun userLogin started...")
        // TODO: Refactor to use retrofit
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient() // v2 LoginRemoteDataSource
            val baseUrl = "https://dragonball.keepcoding.education/api/" // v2 LoginRemoteDataSource
            val url = "${baseUrl}auth/login" // v2 LoginApi

            val credentials = Credentials.basic(email, password) // v2 LoginApi
            Log.d("Tag LoginVM", "credentials: $credentials")
            // prints: Basic ZWpvbHNzb25AZ21haWwuY29tOnZhbW9zUmFmYTIwMjMh

            val formBody = FormBody.Builder().build()
            Log.d("Tag LoginVM", "formBody: $formBody")
            // prints: formBody: okhttp3.FormBody@2949976


            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization",credentials)
                .post(formBody)
                .build()
            Log.d("Tag LoginVM", "request: $request")
            // prints: request: Request{
            // method=POST,
            // url=https://dragonball.keepcoding.education/api/auth/login,
            // headers=[Authorization:Basic ZWpvbHNzb25AZ21haWwuY29tOnZhbW9zUmFmYTIwMjMh]}

            val call = client.newCall(request)
            Log.d("Tag LoginVM", "call: $call")
            // prints: call: okhttp3.internal.connection.RealCall@74b7b77

            val response = call.execute()
            Log.d("Tag LoginVM", "response: $response")
            // prints: response:
            // Response{protocol=http/1.1,
            // code=200,
            // message=OK,
            // url=https://dragonball.keepcoding.education/api/auth/login}

            response.body?.let { responseBody ->
                val tokenPublic = responseBody.string()
                Log.d("Tag LoginVM","Login tokenPublic = $tokenPublic")
                _loginState.value= LoginState.OnLoginReceived(tokenPublic)
            } ?: run { _loginState.value = LoginState.Error("Something went wrong in the request") }
        }
    }

    fun userLogin4(username: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getLogin3(username, password)
            }
            token = result
            _loginState.value = LoginState.OnLoginReceived(token)
        }
    }

    sealed class LoginState {
        object Idle: LoginState()
        data class Error(val error: String): LoginState()
        data class OnLoginReceived(val token: String): LoginState()
        data class LoginRequested(val request: Boolean): LoginState()
    }
}














