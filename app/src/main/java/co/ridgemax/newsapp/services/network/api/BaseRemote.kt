package co.ridgemax.newsapp.services.network.api

import co.ridgemax.newsapp.services.error.ErrorManager
import retrofit2.Response


open class BaseRemote(private val errorManager : ErrorManager) {


    @Suppress("BlockingMethodInNonBlockingContext")
    protected suspend fun <T> parseResult(networkCall: suspend () -> Response<T>): T? {
        val response = networkCall.invoke()
        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            errorManager.getAppError(errorBody)
        }
        return response.body()
    }


}