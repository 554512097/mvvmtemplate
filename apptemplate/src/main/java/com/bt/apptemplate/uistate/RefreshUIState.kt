package com.bt.apptemplate.uistate

import androidx.lifecycle.MutableLiveData

/**
 * Author: kevin
 * Date: 2023/3/15
 * Description:
 */
const val REFRESH_TYPE_REFRESH = 0
const val REFRESH_TYPE_LOAD_MORE = 1

interface RefreshUIState {
    val action: MutableLiveData<Int>
    val finishAction: MutableLiveData<FinishActionArgs>

    val enableLoadMore: MutableLiveData<Boolean>

    val enableRefresh: MutableLiveData<Boolean>
}

class FinishActionArgs(
    val refreshType: Int,
    val delayed: Int = 0,
    val success: Boolean = true,
    val noMoreData: Boolean = false
)