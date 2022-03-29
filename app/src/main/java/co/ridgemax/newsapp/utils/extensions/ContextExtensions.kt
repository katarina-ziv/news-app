package co.ridgemax.newsapp.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.hideKeyboard(
    view: View?
) {
    val imm =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
    view?.clearFocus()
}

fun Context.showKeyboard(
    view: View?
) {
    val imm =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(view?.windowToken, 0, 0)
    view?.clearFocus()
}


fun Context.dp2px(dp: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Int): Int {
    val scale = this.resources.displayMetrics.density
    return px * scale.toInt()
}