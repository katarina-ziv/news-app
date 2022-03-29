package co.ridgemax.newsapp.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.ridgemax.newsapp.utils.extensions.loadCircularImage
import co.ridgemax.newsapp.utils.extensions.loadImage

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.loadImage(imageUrl)
}

@BindingAdapter("android:circularImageUrl", "android:circleWidth", "android:circleColor")
fun loadCircularImage(view: ImageView, circularImageUrl: String?, circleWidth: Float, circleColor: String){
    view.loadCircularImage(circularImageUrl, circleWidth, circleColor)
}
