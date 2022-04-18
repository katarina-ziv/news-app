package co.ridgemax.newsapp.modules.repository

import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.services.database.ArticleDatabase
import co.ridgemax.newsapp.services.network.NetworkModule
import co.ridgemax.newsapp.services.network.api.RetrofitInstance
import retrofit2.Retrofit
class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery : String, pageNumber: Int) =
        RetrofitInstance.api.search(searchQuery,pageNumber)
}
