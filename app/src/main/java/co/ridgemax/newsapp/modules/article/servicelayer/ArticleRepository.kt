package co.ridgemax.newsapp.modules.article.servicelayer

import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.services.network.NetManager
import co.ridgemax.newsapp.services.network.api.BaseRepository
import co.ridgemax.newsapp.services.persistence.room.ArticleDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val remote: ArticleRemote,
    private val netManager: NetManager,
    private val articleDao: ArticleDao
) : BaseRepository() {


    fun getArticles() = retrieveResourceAsFlow {
        if (netManager.isConnectedToInternet()) {
            remote.getArticles()?.articles
        } else {
            throw NoInternetException()
        }
    }

    fun searchNews(searchQuery: String, pageNumber: Int) = retrieveResourceAsFlow {
        if (netManager.isConnectedToInternet()) {
            remote.searchNews(searchQuery, pageNumber)?.articles
        } else {
            throw NoInternetException()
        }
    }

    suspend fun upsert(article: Article) = articleDao.upsert(article)

    suspend fun delete(article: Article) = articleDao.deleteArticle(article)

    fun getSavedNews() = articleDao.getSavedArticles()

    suspend fun isArticleSaved(url: String): Int = articleDao.isArticleSaved(url)

}