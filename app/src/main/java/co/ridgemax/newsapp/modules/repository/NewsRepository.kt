package co.ridgemax.newsapp.modules.repository

import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.services.persistence.room.ArticleDatabase
import co.ridgemax.newsapp.services.network.api.RetrofitInstance

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery : String, pageNumber: Int) =
        RetrofitInstance.api.search(searchQuery,pageNumber)

    suspend fun saveNews(article: Article) =
        db.getArticleDao().upsert(article)
}
