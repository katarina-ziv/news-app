package co.ridgemax.newsapp.modules.auth.servicelayer

import co.ridgemax.newsapp.services.error.ErrorManager
import co.ridgemax.newsapp.services.network.api.ApiService
import co.ridgemax.newsapp.services.network.api.BaseRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemote @Inject constructor(
    private val apiService: ApiService,
    errorManager: ErrorManager
) : BaseRemote(errorManager) {

    suspend fun login(
        email: String,
        password: String
    ) = parseResult { apiService.login(email, password) }

    suspend fun checkUser() = parseResult { apiService.checkUser() }

    suspend fun logout() = parseResult { apiService.logout() }
}