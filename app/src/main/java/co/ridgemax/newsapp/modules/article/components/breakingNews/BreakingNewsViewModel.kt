package co.ridgemax.newsapp.modules.article.components.breakingNews

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.modules.article.models.NewsResponse
import co.ridgemax.newsapp.modules.article.servicelayer.ArticleRepository
import co.ridgemax.newsapp.services.error.NoInternetException
import co.ridgemax.newsapp.utils.Resource
import co.ridgemax.newsapp.utils.enums.UiStates.*
import co.ridgemax.newsapp.utils.enums.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel()
{
    private val _articlesFlow = MutableStateFlow<List<Article>>(arrayListOf())
    val articlesFlow = _articlesFlow.asSharedFlow()

    private val _eventFlow = MutableSharedFlow<UiStates>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
    viewModelScope.launch {
        _eventFlow.emit(
            when (exception) {
                is NoInternetException -> NO_INTERNET_CONNECTION
                else -> UNKNOWN_ERROR
            }
        )
    }

    }
    private fun getArticles()
    {
        viewModelScope.launch(handler)
        {
            repository.getArticles().collect {
                Log.d("test","${it.toString()}")
                if (it != null) {
                    _articlesFlow.emit(it)
                }
            }
        }
    }

    //radi kad se stavi na kraju
    init {
        Log.d("test","getArticle")
        getArticles()
    }
}