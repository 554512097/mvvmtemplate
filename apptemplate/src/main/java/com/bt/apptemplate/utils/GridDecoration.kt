package com.bt.apptemplate.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridDecoration(private val horizontalSpacing: Int, private val verticalSpacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as? androidx.recyclerview.widget.GridLayoutManager)?.spanCount ?: 1

        val column = position % spanCount
        outRect.left = horizontalSpacing - column * horizontalSpacing / spanCount
        outRect.right = (column + 1) * horizontalSpacing / spanCount

        if (position < spanCount) {
            outRect.top = verticalSpacing
        }
        outRect.bottom = verticalSpacing
    }
}