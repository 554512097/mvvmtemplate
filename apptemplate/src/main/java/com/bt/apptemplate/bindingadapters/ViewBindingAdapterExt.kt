package com.bt.apptemplate.bindingadapters

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ConvertUtils

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun setVisibleOrInvisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter(
    "radiusTopLeft",
    "radiusTopRight",
    "radiusBottomLeft",
    "radiusBottomRight",
    "radiusBackgroundColor",
    requireAll = false
)
fun setBackgroundWithRadiusAndColor(
    view: View,
    radiusTopLeft: Float = 0f,
    radiusTopRight: Float = 0f,
    radiusBottomLeft: Float = 0f,
    radiusBottomRight: Float = 0f,
    radiusBackgroundColor: Int?
) {
    val radiusTopLeftInDp = ConvertUtils.dp2px(radiusTopLeft)
    val radiusTopRightInDp = ConvertUtils.dp2px(radiusTopRight)
    val radiusBottomLeftInDp = ConvertUtils.dp2px(radiusBottomLeft)
    val radiusBottomRightInDp = ConvertUtils.dp2px(radiusBottomRight)
    view.background = GradientDrawable().apply {
        cornerRadii = floatArrayOf(
            radiusTopLeftInDp.toFloat(), radiusTopLeftInDp.toFloat(),
            radiusTopRightInDp.toFloat(), radiusTopRightInDp.toFloat(),
            radiusBottomLeftInDp.toFloat(), radiusBottomLeftInDp.toFloat(),
            radiusBottomRightInDp.toFloat(), radiusBottomRightInDp.toFloat()
        )
        radiusBackgroundColor?.also {
            setColor(it)
        }
    }
}
