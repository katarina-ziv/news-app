package co.ridgemax.newsapp.services.persistence

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import co.ridgemax.newsapp.modules.user.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferences @Inject constructor(@ApplicationContext private val context: Context, moshi: Moshi) {

    private val prefs = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE)
    private val userAdapter = moshi.adapter(User::class.java)

    var accessToken: String
        get() = prefs.getString(ACCESS_TOKEN, "") ?: ""
        set(value) = prefs.edit().putString(ACCESS_TOKEN, value).apply()

//    var apiKey : String
//        get() = prefs.getString(API_KEY,"")?: ""
//        set(value) = prefs.edit().putString(API_KEY,value).apply()

    var user: User?
        get() {
            val json = prefs.getString(USER, "")
            return if (json.isNullOrEmpty()) null else userAdapter.fromJson(json)
        }
        set(value) {
            val json = userAdapter.toJson(value)
            prefs.edit().putString(USER, json).apply()
        }

    companion object {
        private const val PREF_FILENAME = "me.slavzilla.baseproject.pref"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val USER = "USER"
        private const val API_KEY = "API_KEY"
    }
}