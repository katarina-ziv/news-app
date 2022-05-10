package co.ridgemax.newsapp.modules.article.components.breakingNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.databinding.FragmentBreakingNewsBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import co.ridgemax.newsapp.modules.article.adapters.NewsPagingAdapter
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
    lateinit var newsAdapter: NewsPagingAdapter


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

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun instantiateUi()
    {
        newsAdapter = NewsPagingAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter

        }
    }

    private fun observeViewModel()
    {
//        lifecycleScope.launchWhenCreated {
//            viewModel.articlesFlow.collectLatest {
//                Log.d("test","${it.toString()}")
//                newsAdapter.updateList(it)
//            }
//        }

        lifecycleScope.launchWhenCreated {
            viewModel.fetchArticles().collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }
    }

}