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
import co.ridgemax.newsapp.databinding.FragmentBreakingNewsBinding
import co.ridgemax.newsapp.databinding.FragmentFavoritesBinding
import co.ridgemax.newsapp.modules.article.components.search.SearchViewModel
import co.ridgemax.newsapp.modules.article.models.Article
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val viewModel by viewModels<ArticleViewModel>()
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        Glide.with(binding.root.context)
            .load(article.urlToImage)
            .into(article_img)
        article_title.text = article.title
        source_content.text = article.source.name
        hour_published.text = article.publishedAt.substring(11,16)
        article_author.text = article.author
        article_content.text = article.content

    }
}

