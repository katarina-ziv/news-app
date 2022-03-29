package co.ridgemax.newsapp.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import co.ridgemax.newsapp.modules.auth.servicelayer.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val user = authRepository.user

    fun isUserLoggedIn() = authRepository.isUserLoggedIn()

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}