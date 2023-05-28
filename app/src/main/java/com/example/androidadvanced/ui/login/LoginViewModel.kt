package com.example.androidadvanced.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    private val repository = LoginRepository()
    private var token: String = ""

    fun userLogin4(username: String, password: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getLogin3(username, password)
            }
            token = result
            _loginState.value = LoginState.LoginSuccess(token)
        }
    }

    fun skipLogin() {
        viewModelScope.launch {
            val result = "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"
            token = result
            _loginState.value = LoginState.LoginSuccess(token)
        }
    }

    sealed class LoginState {
        object Idle: LoginState()
        data class Error(val error: String): LoginState()
        data class LoginSuccess(val token: String): LoginState()
        data class LoginRequested(val request: Boolean): LoginState()
    }
}














