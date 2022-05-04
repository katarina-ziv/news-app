package co.ridgemax.newsapp.modules.article.components.favorites

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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: ArticleRepository) :
    ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiStates>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _savedNews = MutableStateFlow<List<Article>>(arrayListOf())
    val savedNews = _savedNews.asSharedFlow()


    private val handleSavedNewsResponse = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            _eventFlow.emit(
                when (exception) {
                    is NoInternetException -> UiStates.NO_INTERNET_CONNECTION
                    else -> UiStates.UNKNOWN_ERROR
                }
            )
        }
    }

    fun saveNews() {
        viewModelScope.launch(handleSavedNewsResponse) {
            repository.getSavedNews().collect {
                _savedNews.emit(it)
            }
        }
    }


    //fun getSavedNews() = repository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.delete(article)
    }
}