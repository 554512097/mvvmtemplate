package com.bt.apptemplate.bindingadapters

import androidx.databinding.BindingAdapter
import com.bt.apptemplate.uistate.FinishActionArgs
import com.bt.apptemplate.uistate.REFRESH_TYPE_LOAD_MORE
import com.bt.apptemplate.uistate.REFRESH_TYPE_REFRESH
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * Author: kevin
 * Date: 2023/3/15
 * Description:
 */

@BindingAdapter("action")
fun setAction(srl: SmartRefreshLayout, action: Int): Unit {
    when (action) {
        REFRESH_TYPE_REFRESH -> srl.autoRefresh()
        REFRESH_TYPE_LOAD_MORE -> srl.autoLoadMore()
    }
}

@BindingAdapter("finishAction")
fun setFinishAction(srl: SmartRefreshLayout, finishAction: FinishActionArgs): Unit {
    when (finishAction.refreshType) {
        REFRESH_TYPE_REFRESH -> srl.finishRefresh(
            finishAction.delayed,
            finishAction.success,
            finishAction.noMoreData
        )
        REFRESH_TYPE_LOAD_MORE -> srl.finishLoadMore(
            finishAction.delayed,
            finishAction.success,
            finishAction.noMoreData
        )
    }
}