package co.ridgemax.newsapp.modules.article.components.breakingNews

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.ridgemax.newsapp.databinding.FragmentBreakingNewsBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import co.ridgemax.newsapp.utils.Resource
import co.ridgemax.newsapp.utils.enums.UiStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Response

@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {

    private lateinit var binding: FragmentBreakingNewsBinding
    private val viewModel by viewModels<BreakingNewsViewModel>()
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiateUi()
        observeViewModel()
    }

    private fun instantiateUi()
    {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
        }
    }

    private fun observeViewModel()
    {
        lifecycleScope.launchWhenCreated {
            viewModel.articlesFlow.collectLatest {
                newsAdapter.updateList(it)
            }
        }
    }

}