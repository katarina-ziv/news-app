package co.ridgemax.newsapp.modules.article.components.favorites

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainActivity
import co.ridgemax.newsapp.activities.main.MainViewModel
import co.ridgemax.newsapp.databinding.FragmentFavoritesBinding
import co.ridgemax.newsapp.databinding.FragmentSearchBinding
import co.ridgemax.newsapp.modules.article.adapters.NewsAdapter
import co.ridgemax.newsapp.modules.article.components.search.SearchViewModel
import co.ridgemax.newsapp.modules.article.components.singleArticle.ArticleViewModel
import co.ridgemax.newsapp.modules.article.models.Article
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.Collections.list

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModels<FavoritesViewModel>()
    private val articleViewModel by viewModels<ArticleViewModel>()
    lateinit var newsAdapter: NewsAdapter
    val TAG = "FavoritesFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instantiateUi()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                co.ridgemax.newsapp.R.id.action_favoritesFragment_to_articleFragment,
                bundle
            )
        }
        viewModel.saveNews()
        observeViewModel()
        swipeToDelete()
    }

    private fun swipeToDelete() {
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
                val position = viewHolder.bindingAdapterPosition
                val article = newsAdapter.list[position]
                viewModel.deleteArticle(article)
                newsAdapter.notifyItemRemoved(position)
                Snackbar.make(binding.root, "Deleted article", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            articleViewModel.saveArticle(article)
                        }
                    }.show()
            }


            //RecyclerView Swipe decoration
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int,
                isCurrentlyActive: Boolean) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                    .addSwipeLeftLabel("Delete")
                    .addActionIcon(R.drawable.ic_trash)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    .setSwipeLeftLabelTextSize(actionState,16f)
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })

        itemTouchHelperCallback.apply {
            attachToRecyclerView(binding.rvFavoriteNews)
        }
    }

    private fun instantiateUi() {
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