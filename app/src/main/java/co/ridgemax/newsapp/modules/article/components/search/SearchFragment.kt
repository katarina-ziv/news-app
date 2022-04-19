package co.ridgemax.newsapp.modules.article.components.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainViewModel
import co.ridgemax.newsapp.databinding.FragmentBreakingNewsBinding
import co.ridgemax.newsapp.databinding.FragmentSearchBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import co.ridgemax.newsapp.modules.article.components.breakingNews.BreakingNewsViewModel
import co.ridgemax.newsapp.utils.Constants.SEARCH_NEWS_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    lateinit var newsAdapter: NewsAdapter
    val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiateUi()
        observeViewModel()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_articleFragment,
                bundle
            )
        }
        //delay
        var job: Job? = null
        et_search.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY) //500ms
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun instantiateUi() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.searchNews.collectLatest {
                newsAdapter.updateList(it)
            }
        }
    }
}