package co.ridgemax.newsapp.modules.article.servicelayer

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.services.error.ErrorManager
import co.ridgemax.newsapp.services.network.api.ApiService
import co.ridgemax.newsapp.services.network.api.BaseRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRemote @Inject constructor(
    private val apiService: ApiService,
    errorManager: ErrorManager
) : BaseRemote(errorManager) {
    suspend fun getArticles() = parseResult { apiService.getTopNews() }

    suspend fun searchNews(searchQuery: String, pageNumber: Int, language: String) =
        parseResult { apiService.search(searchQuery, pageNumber, language) }


    fun fetchArticles(): Flow<PagingData<Article>> {
        // 3
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false)
        ) {
            NewsPagingSource(apiService)
        }.flow
    }

}