package co.ridgemax.newsapp.modules.user.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "activated")
    val activated: Int = 0,
    @Json(name = "address")
    val address: String = "",
    @Json(name = "banned")
    val banned: Int = 0,
    @Json(name = "birthdate")
    val birthdate: String = "",
    @Json(name = "city")
    val city: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "full_name")
    val fullName: String = "",
    @Json(name = "gender")
    val gender: String = "",
    @Json(name = "group")
    val group: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "locale")
    val locale: String = "",
    @Json(name = "telephone")
    val telephone: String = ""
)