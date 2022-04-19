package co.ridgemax.newsapp.modules.article.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.ridgemax.newsapp.databinding.ArticlePreviewBinding
import co.ridgemax.newsapp.modules.article.models.Article
import com.bumptech.glide.Glide


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val list: ArrayList<Article> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Article>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = list[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(article) }
        }
        holder.bind(article)
    }

    override fun getItemCount() = list.size

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    class ArticleViewHolder(private val binding: ArticlePreviewBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(article: Article) {

            with (binding)
            {
                Glide.with(binding.root.context)
                    .load(article.urlToImage)
                    .into(articleImage)
                tvArticleTitle.text = article.title
                tvArticleSource.text = article.source.name

            }
        }
    }
}
