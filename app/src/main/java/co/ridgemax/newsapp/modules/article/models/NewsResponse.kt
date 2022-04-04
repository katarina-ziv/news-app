package co.ridgemax.newsapp.modules.article.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)