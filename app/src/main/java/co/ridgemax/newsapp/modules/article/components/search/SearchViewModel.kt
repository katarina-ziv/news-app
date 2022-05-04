package co.ridgemax.newsapp.modules.article.components.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.modules.article.servicelayer.ArticleRepository
import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.utils.enums.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiStates>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _searchNews = MutableStateFlow<List<Article>>(arrayListOf())
    val searchNews = _searchNews.asSharedFlow()
    var searchNewsPage = 1

    private val handleSearchNewsResponse = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            _eventFlow.emit(
                when (exception) {
                    is NoInternetException -> UiStates.NO_INTERNET_CONNECTION
                    else -> UiStates.UNKNOWN_ERROR
                }
            )
        }
    }

    fun searchNews(searchQuery: String){
        viewModelScope.launch (handleSearchNewsResponse){
            repository.searchNews(searchQuery, searchNewsPage).collect{
                if(it != null){
                    _searchNews.emit(it)
                }
            }
        }
    }

}

