package co.ridgemax.newsapp.modules.article.components.singleArticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainViewModel
import co.ridgemax.newsapp.databinding.FragmentArticleBinding
import co.ridgemax.newsapp.databinding.FragmentFavoritesBinding
import co.ridgemax.newsapp.modules.article.components.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val viewModel by viewModels<SearchViewModel>()
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        webView.apply{
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}