package co.ridgemax.newsapp.services.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData<T> (
    @Json(name = "data")
    val data: T,
    @Json(name = "message")
    val message: String,
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "access_token")
    val accessToken: String
)