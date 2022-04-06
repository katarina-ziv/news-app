package co.ridgemax.newsapp.services.database

import androidx.room.TypeConverter
import co.ridgemax.newsapp.modules.article.models.Source
import java.util.*


class ArticleTypeConverters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}
