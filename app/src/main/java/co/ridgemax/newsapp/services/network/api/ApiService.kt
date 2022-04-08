package co.ridgemax.newsapp.services.network.api


import co.ridgemax.newsapp.modules.article.models.NewsResponse
import co.ridgemax.newsapp.modules.user.models.User
import co.ridgemax.newsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

//    @FormUrlEncoded
//    @POST("auth/login")
//    suspend fun login(
//        @Field("email") email: String,
//        @Field("password") password: String,
//    ): Response<ApiData<User>>
//
//    @POST("auth/check")
//    suspend fun checkUser(): Response<ApiData<User>>
//
//    @POST("auth/logout")
//    suspend fun logout(): Response<ApiData<User>>

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        country: String = "us",
        @Query("page")
        pageNo : Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun search(
        @Query("q")
        query: String,
        @Query("page")
        pageNo : Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ) : Response<NewsResponse>
}