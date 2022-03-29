package co.ridgemax.newsapp.utils.extensions

import androidx.fragment.app.Fragment

fun Fragment.getStringOrNull(id: Int?) = if (id != null) getString(id) else null