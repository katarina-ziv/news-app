package co.ridgemax.newsapp.modules.article.components.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainViewModel
import co.ridgemax.newsapp.databinding.FragmentFavoritesBinding
import co.ridgemax.newsapp.databinding.FragmentSearchBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import co.ridgemax.newsapp.modules.article.components.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModels<FavoritesViewModel>()
    lateinit var newsAdapter: NewsAdapter
    val TAG = "FavoritesFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instantiateUi()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.updateList(articles)
        })
    }


    private fun instantiateUi()
    {
        newsAdapter = NewsAdapter()
        binding.rvFavoriteNews.apply {
            adapter = newsAdapter
        }
    }



//    private fun observeViewModel() {
//        lifecycleScope.launchWhenCreated {
//            viewModel.getSavedNews().collectLatest {
//                newsAdapter.updateList(it)
//            }
//
//        }
//    }

}