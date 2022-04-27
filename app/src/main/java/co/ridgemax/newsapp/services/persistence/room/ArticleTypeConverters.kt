package co.ridgemax.newsapp.services.persistence.room

import androidx.room.TypeConverter
import co.ridgemax.newsapp.modules.article.models.Source


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
