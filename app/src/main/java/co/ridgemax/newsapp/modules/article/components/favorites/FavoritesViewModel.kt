package co.ridgemax.newsapp.modules.article.components.favorites

import androidx.lifecycle.ViewModel
import co.ridgemax.newsapp.modules.article.servicelayer.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {

}