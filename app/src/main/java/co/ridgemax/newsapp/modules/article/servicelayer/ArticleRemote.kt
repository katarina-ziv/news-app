package co.ridgemax.newsapp.modules.article.servicelayer

import co.ridgemax.newsapp.services.error.ErrorManager
import co.ridgemax.newsapp.services.network.api.ApiService
import co.ridgemax.newsapp.services.network.api.BaseRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRemote @Inject constructor(
    private val apiService: ApiService,
    errorManager: ErrorManager
) : BaseRemote(errorManager)
{
    suspend fun getArticles() = parseResult { apiService.getTopNews() }
}