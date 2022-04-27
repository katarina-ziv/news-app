package co.ridgemax.newsapp.modules

import android.content.Context
import androidx.room.Room
import co.ridgemax.newsapp.services.persistence.room.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideArticleDatabase(
        @ApplicationContext app: Context)
    = Room.databaseBuilder(
        app,
        ArticleDatabase::class.java,
        "article.db"
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.getArticleDao()

}