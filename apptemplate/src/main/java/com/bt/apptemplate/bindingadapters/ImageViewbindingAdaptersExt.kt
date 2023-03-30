package com.bt.apptemplate.bindingadapters

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Author: kevin
 * Date: 2023/3/15
 * Description:
 */

@BindingAdapter("android:src")
fun setImageRes(iv: ImageView, res: Int): Unit {
    iv.setImageResource(res)
}

@BindingAdapter("autoRotate")
fun setImageAutoRotate(iv: ImageView, autoRotate: Boolean): Unit {
    if (autoRotate) {
        val loading = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            .5f,
            Animation.RELATIVE_TO_SELF,
            .5f
        )
        loading.repeatCount = Animation.INFINITE
        loading.repeatMode = Animation.RESTART
        loading.duration = 1000
        loading.interpolator = LinearInterpolator()
        iv.startAnimation(loading)
    }
}