package com.bt.apptemplate.uistate

import android.widget.Toast
import androidx.lifecycle.MutableLiveData

/**
 * Author: kevin
 * Date: 2023/3/2
 * Description:
 */
interface ToastState {
    val toastState: MutableLiveData<ToastStateArgs>
}

class ToastStateArgs(val msg: String, val duration: Int = Toast.LENGTH_SHORT)