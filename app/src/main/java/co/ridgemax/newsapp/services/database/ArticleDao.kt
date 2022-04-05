package co.ridgemax.newsapp.services.database

import androidx.room.*
import co.ridgemax.newsapp.modules.article.models.Article
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : StateFlow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}