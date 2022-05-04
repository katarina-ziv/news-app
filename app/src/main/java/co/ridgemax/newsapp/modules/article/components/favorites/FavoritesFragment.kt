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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

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
        viewModel.saveNews()
        observeViewModel()
        swipeToDelete()
    }

    private fun swipeToDelete(){
        val itemTouchHelperCallback = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        })

        itemTouchHelperCallback.apply {
            attachToRecyclerView(binding.rvFavoriteNews)
        }
    }


    private fun instantiateUi()
    {
        newsAdapter = NewsAdapter()
        binding.rvFavoriteNews.apply {
            adapter = newsAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.savedNews.collectLatest {
                newsAdapter.updateList(it)
            }
        }
    }
}