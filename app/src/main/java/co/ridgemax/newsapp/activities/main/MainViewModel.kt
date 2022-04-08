package co.ridgemax.newsapp.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ridgemax.newsapp.modules.article.models.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import co.ridgemax.newsapp.modules.auth.servicelayer.AuthRepository
import co.ridgemax.newsapp.modules.repository.NewsRepository
import co.ridgemax.newsapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject


class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    init {
        getTopNews("us")
    }

    fun getTopNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
//    val user = authRepository.user
//
//    fun isUserLoggedIn() = authRepository.isUserLoggedIn()
//
//    fun logout() {
//        viewModelScope.launch {
//            authRepository.logout()
//        }
//    }
}