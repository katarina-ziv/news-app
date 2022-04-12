package co.ridgemax.newsapp.modules.article.components.breakingNews

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.ridgemax.newsapp.databinding.FragmentBreakingNewsBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //TODO provjeriti
//        setupRecyclerView()
//
//
//        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
//            when (response) {
//                is Resource.Success -> {
//                 //   hideProgressBar()
//                    response.data?.let { newsResponse ->
//                        //newsAdapter.differ.submitList(newsResponse.articles)
//                    }
//                }
//                is Resource.Error -> {
//                    response.message?.let {message ->
//                        Log.e("BreakingNewsFragment","An error occured $message")
//                    }
//                }
//                is Resource.Loading->{
//                    //
//                }
//            }
//
//        })
//    }
////
////    private fun hideProgressBar(){
////        paginationProgressBar.visibility = View.INVISIBLE
////    }
////    private fun showProgressBar(){
////        paginationProgressBar.visibility = View.VISIBLE
////    }

}