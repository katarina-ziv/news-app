package co.ridgemax.newsapp.modules.article.servicelayer


import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.services.network.api.ApiService
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val apiService: ApiService,
    private val country: String = "us",
    private val pageSize: Int = 5
) : PagingSource<String, Article>() {

    private var page = 1

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Article> {
        return try {
            val response = apiService.getArticles(country, page, pageSize = pageSize)
            // 2
            val articles = response.body()?.articles
            val size = articles?.size ?: 0
            // 3
            LoadResult.Page(
                articles ?: listOf(),
                (if (page > 1) (page - 1).toString() else null),
                (if (size < pageSize) null else (++page).toString())
            )
        } catch (exception: IOException) { // 6
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Article>): String? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey
            }
        //provjeriti sta je ovo sranje
    }

}


