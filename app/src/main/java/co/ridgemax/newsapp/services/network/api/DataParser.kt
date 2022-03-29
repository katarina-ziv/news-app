package co.ridgemax.newsapp.services.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataParser<T>(
    @Json(name = "data")
    val data: List<T>?
)