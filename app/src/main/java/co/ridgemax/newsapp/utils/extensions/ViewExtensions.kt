package co.ridgemax.newsapp.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import co.ridgemax.newsapp.utils.listeners.OnSingleClickListener

fun View.showViewUsingCrossFade(duration: Long) {
    this.apply {
        alpha = 0f
        visibility = View.VISIBLE

        animate()
            .alpha(1f)
            .setDuration(duration)
            .setListener(null)
    }
}

fun View.hideViewUsingCrossFade(duration: Long){
    this.animate()
        .alpha(0f)
        .setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.INVISIBLE
            }
        })
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}
