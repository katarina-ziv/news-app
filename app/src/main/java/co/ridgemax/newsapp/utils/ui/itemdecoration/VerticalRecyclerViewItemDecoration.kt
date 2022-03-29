package co.ridgemax.newsapp.utils.ui.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalRecyclerViewItemDecoration(
    private val spaceHeight: Int,
    private val firstHeight: Int,
    private val lastHeight: Int = 0
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = firstHeight
            }
            bottom = spaceHeight
            if (lastHeight != 0 && parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                bottom = lastHeight + spaceHeight
            }
        }
    }
}