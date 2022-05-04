package co.ridgemax.newsapp.services.persistence.room

import androidx.room.*
import co.ridgemax.newsapp.modules.article.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getSavedArticles() : Flow<List<Article>>

    @Query("SELECT COUNT(url) FROM articles WHERE url = :url")
    suspend fun isArticleSaved(url: String) : Int

    @Delete
    suspend fun deleteArticle(article: Article)

}