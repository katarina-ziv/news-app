package co.ridgemax.newsapp.utils.ui.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalRecyclerViewItemDecoration(private val spaceHeight: Int, private val startingMargin: Int = 0) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = startingMargin
            }
            right = spaceHeight
        }
    }
}