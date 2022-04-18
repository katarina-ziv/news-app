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

}