package co.ridgemax.newsapp.modules.article.servicelayer

import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.services.network.NetManager
import co.ridgemax.newsapp.services.network.api.BaseRepository
import co.ridgemax.newsapp.services.network.api.RetrofitInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val remote: ArticleRemote,
    private val netManager: NetManager
) : BaseRepository() {

    fun getArticles() = retrieveResourceAsFlow {
        if (netManager.isConnectedToInternet()) {
            remote.getArticles()?.articles
        } else {
            throw NoInternetException()
        }
    }

    fun searchNews(searchQuery: String,pageNumber: Int) = retrieveResourceAsFlow {
        if(netManager.isConnectedToInternet()){
            remote.searchNews(searchQuery,pageNumber)?.articles
        }else{
            throw NoInternetException()
        }
    }

}