package co.ridgemax.newsapp.modules.auth.servicelayer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import co.ridgemax.newsapp.services.error.AccessTokenRequiredException
import co.ridgemax.newsapp.services.error.BadAccessTokenException
import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.services.network.NetManager
import co.ridgemax.newsapp.services.network.api.BaseRepository
import co.ridgemax.newsapp.services.persistence.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val remote: AuthRemote,
    private val sharedPrefs: SharedPreferences,
    private val netManager: NetManager
) : BaseRepository() {

    private val _user = MutableStateFlow(sharedPrefs.user)
    val user = _user.asStateFlow()

    init {
        GlobalScope.launch {
            try {
                //checkUser()
            } catch (e: Exception) {
                if (e is BadAccessTokenException || e is AccessTokenRequiredException) {
                    localLogout()
                }
            }
        }
    }

//    fun login(email: String, password: String) = retrieveResourceAsFlow {
//        if (netManager.isConnectedToInternet()) {
//            val response = remote.login(email, password)
//            response?.let {
//                sharedPrefs.accessToken = it.accessToken
//                sharedPrefs.user = it.data
//                _user.value = it.data
//            }
//            response?.data
//        } else {
//            throw NoInternetException()
//        }
//
//    }
//
//    private suspend fun checkUser() {
//        val userResponse = remote.checkUser()
//        userResponse?.let {
//            _user.value = it.data
//            sharedPrefs.accessToken = it.accessToken
//            sharedPrefs.user = it.data
//        }
//    }
//
//    suspend fun logout() {
//        if (netManager.isConnectedToInternet()) {
//            val response = remote.logout()
//            if (response?.statusCode == 200) {
//                localLogout()
//            }
//        } else {
//            throw NoInternetException()
//        }
//    }

    private fun localLogout() {
        _user.value = null
        sharedPrefs.accessToken = ""
        sharedPrefs.user = null
    }


    fun isUserLoggedIn() = sharedPrefs.accessToken.isNotEmpty()
}