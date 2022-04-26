package co.ridgemax.newsapp.modules.article.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Entity(
   tableName ="articles"
)
@JsonClass(generateAdapter = true)
data class Article(
    @PrimaryKey(autoGenerate = true)
    @Json(name="id")
    var id: Int? = null,
    @Json(name="author")
    val author: String? = null,
    @Json(name="content")
    val content: String? = null,
    @Json(name="description")
    val description: String? = null,
    @Json(name="publishedAt")
    val publishedAt: String,
    @Json(name="source")
    val source: Source,
    @Json(name="title")
    val title: String? = null,
    @Json(name="url")
    val url: String? = null,
    @Json(name="urlToImage")
    val urlToImage: String? = null
) : Serializable