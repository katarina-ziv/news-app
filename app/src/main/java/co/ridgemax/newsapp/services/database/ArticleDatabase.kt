package co.ridgemax.newsapp.services.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.ridgemax.newsapp.modules.article.models.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(ArticleTypeConverters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val articleDao: ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "article_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}