package co.ridgemax.newsapp.modules.article.components.singleArticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ridgemax.newsapp.modules.article.models.Article
import co.ridgemax.newsapp.modules.article.servicelayer.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {


    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.upsert(article)
    }

}

