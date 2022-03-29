package co.ridgemax.newsapp.utils.extensions

import android.text.TextUtils
import androidx.core.graphics.toColorInt

fun String?.toColor(defaultColor: String = "#00000000"): Int {
    if (this.isNullOrEmpty()) return defaultColor.toColorInt()
    return this.toColorInt()
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}