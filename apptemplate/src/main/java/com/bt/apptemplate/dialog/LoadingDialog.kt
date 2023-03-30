package com.bt.apptemplate.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bt.apptemplate.R

/**
 * Author: kevin
 * Date: 2023/3/10
 * Description:
 */
class LoadingDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ImageView(context)
        view.setImageResource(R.drawable.loading)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        view.startAnimation(loading)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}