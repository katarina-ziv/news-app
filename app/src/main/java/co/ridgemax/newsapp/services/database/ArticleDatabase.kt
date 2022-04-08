package co.ridgemax.newsapp.services.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.ridgemax.newsapp.activities.main.MainActivity
import co.ridgemax.newsapp.modules.article.components.breakingNews.BreakingNewsFragment
import co.ridgemax.newsapp.modules.article.models.Article

@Database(
    entities = [Article::class],
    version = 2
)
@TypeConverters(ArticleTypeConverters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: MainActivity) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article.db"
            ).build()
    }
}