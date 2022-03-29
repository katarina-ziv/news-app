package co.ridgemax.newsapp.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
//         .placeholder(R.drawable.promo_segment)
//        .error(R.drawable.promo_segment)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun ImageView.loadCircularImage(uri: String?, circleWidth: Float, circleColor: String){
    val options = RequestOptions()
        .placeholder(android.R.color.transparent)
    Glide.with(context).setDefaultRequestOptions(options).load(uri)
        .apply {
            if (width > 0) {
                apply(
                    RequestOptions.bitmapTransform(
                        CropCircleWithBorderTransformation(
                            context.dp2px(
                                circleWidth
                            ), circleColor.toColor()
                        )
                    )
                )
            } else {
                apply(RequestOptions.circleCropTransform())
            }
        }
        .into(this)
}