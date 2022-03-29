package co.ridgemax.newsapp.services.network.api


import co.ridgemax.newsapp.modules.user.models.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<ApiData<User>>

    @POST("auth/check")
    suspend fun checkUser(): Response<ApiData<User>>

    @POST("auth/logout")
    suspend fun logout(): Response<ApiData<User>>
}