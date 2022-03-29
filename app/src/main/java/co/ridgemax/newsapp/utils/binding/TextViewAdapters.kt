package co.ridgemax.newsapp.utils.binding

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("android:bindHtml")
fun bindHtml(view: TextView, bindHtml: String) {
    view.text = HtmlCompat.fromHtml(bindHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
}