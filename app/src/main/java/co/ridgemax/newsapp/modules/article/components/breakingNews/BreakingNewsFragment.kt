package co.ridgemax.newsapp.modules.article.components.breakingNews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainActivity
import co.ridgemax.newsapp.activities.main.MainViewModel
import co.ridgemax.newsapp.activities.main.MainViewModelProviderFactory
import co.ridgemax.newsapp.modules.adapters.NewsAdapter
import co.ridgemax.newsapp.modules.repository.NewsRepository
import co.ridgemax.newsapp.services.database.ArticleDatabase
import co.ridgemax.newsapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: MainViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO provjeriti
        viewModel = (activity as MainActivity).viewModel as MainViewModel
        setupRecyclerView()


        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                 //   hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let {message ->
                        Log.e("BreakingNewsFragment","An error occured $message")
                    }
                }
                is Resource.Loading->{
                    //
                }
            }

        })
    }
//
//    private fun hideProgressBar(){
//        paginationProgressBar.visibility = View.INVISIBLE
//    }
//    private fun showProgressBar(){
//        paginationProgressBar.visibility = View.VISIBLE
//    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}