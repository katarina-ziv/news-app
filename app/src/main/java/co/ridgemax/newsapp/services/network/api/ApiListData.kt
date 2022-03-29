package co.ridgemax.newsapp.services.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiListData<T>(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status_code")
    val statusCode: Int?,
    @Json(name = "data")
    var data: DataParser<T>,
    @Json(name = "error_code")
    var errorCode: String? = null
)