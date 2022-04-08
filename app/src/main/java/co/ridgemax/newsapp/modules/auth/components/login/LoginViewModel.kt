package co.ridgemax.newsapp.modules.auth.components.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.modules.auth.servicelayer.AuthRepository
import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.services.error.UserNotFoundException
import co.ridgemax.newsapp.utils.enums.UiStates
import co.ridgemax.newsapp.utils.enums.UiStates.*
import co.ridgemax.newsapp.utils.extensions.isEmailValid
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
}
//    private val _eventFlow = MutableSharedFlow<UiStates>()
//    val eventFlow = _eventFlow.asSharedFlow()
//
//    val loginErrorFlow = MutableSharedFlow<Int?>()
//
//    val passwordErrorFlow = MutableSharedFlow<Int?>()
//
//    private val handler = CoroutineExceptionHandler { _, exception ->
//        viewModelScope.launch {
//            _eventFlow.emit(
//                when (exception) {
//                    is UserNotFoundException -> USER_NOT_FOUND
//                    is NoInternetException -> NO_INTERNET_CONNECTION
//                    else -> UNKNOWN_ERROR
//                }
//            )
//        }
//
//    }

//    fun login(email: String, password: String) {
//        viewModelScope.launch(handler) {
//            if (validateForm(email, password)) {
////                authRepository.login(email, password).collect {
////                    _eventFlow.emit(LOGIN)
//                }
//            }
//        }
//    }

 //   private suspend fun validateForm(email: String, password: String): Boolean {
 //       var isValid = true
//
//        //email
//        loginErrorFlow.emit(
//            when {
//                email.isBlank() -> {
//                    isValid = false
//                    R.string.alert_loc_login_ctx_empty_mail
//                }
//                !email.isEmailValid() -> {
//                    isValid = false
//                    R.string.alert_loc_login_ctx_email_not_valid
//                }
//                else -> null
//            }
//        )
//
//        //password
//        if (password.length < 6) {
//            passwordErrorFlow.emit(R.string.alert_loc_login_ctx_empty_password)
//            isValid = false
//        } else {
//            passwordErrorFlow.emit(null)
//        }
  //      return isValid

