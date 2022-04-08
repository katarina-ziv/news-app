package co.ridgemax.newsapp.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.ridgemax.newsapp.modules.repository.NewsRepository

class MainViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(newsRepository) as T
    }
}