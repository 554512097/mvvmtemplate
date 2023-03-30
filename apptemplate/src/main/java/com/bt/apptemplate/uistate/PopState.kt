package com.bt.apptemplate.uistate

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData

/**
 * Author: kevin
 * Date: 2023/3/2
 * Description:
 */
interface PopState {
    val popState: MutableLiveData<PopStateArgs>
}

const val ONLY_POP_STACK = 0

class PopStateArgs(
    @IdRes val destinationId: Int = ONLY_POP_STACK,
    val resultData: Any? = null,
    val inclusive: Boolean = false,
    val saveState: Boolean = false,
)