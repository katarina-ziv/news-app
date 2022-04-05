package co.ridgemax.newsapp.services.network.api

import co.ridgemax.newsapp.modules.article.models.NewsResponse
import co.ridgemax.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        country: String = "us",
        @Query("page")
        pageNo : Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun search(
        @Query("q")
        query: String,
        @Query("page")
        pageNo : Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>
}