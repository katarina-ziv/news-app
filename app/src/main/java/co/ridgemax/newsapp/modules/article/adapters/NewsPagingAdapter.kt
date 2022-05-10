package co.ridgemax.newsapp.modules.article.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import co.ridgemax.newsapp.databinding.ArticlePreviewBinding
import co.ridgemax.newsapp.modules.article.models.Article
import com.bumptech.glide.Glide

class NewsPagingAdapter : PagingDataAdapter<Article, NewsPagingAdapter.ArticleViewHolder>(DiffUtilCallBack()) {


    class ArticleViewHolder(private val binding: ArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

            with(binding)
            {
                Glide.with(binding.root.context)
                    .load(article.urlToImage)
                    .into(articleImage)
                tvArticleTitle.text = article.title
                tvArticleSource.text = article.source.name
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.bind(article)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsPagingAdapter.ArticleViewHolder(binding)
    }
}