package co.ridgemax.newsapp.modules.article.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name="id")
    val id: String? = null,
    @Json(name="name")
    val name: String
)