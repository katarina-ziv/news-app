package co.ridgemax.newsapp.modules.article.adapters

import androidx.recyclerview.widget.DiffUtil
import co.ridgemax.newsapp.modules.article.models.Article

class DiffUtilCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
                && oldItem.content == newItem.content
                && oldItem.description == newItem.description
    }
}