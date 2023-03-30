package com.bt.apptemplate.uistate

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.bt.apptemplate.R

/**
 * Author: kevin
 * Date: 2023/3/15
 * Description:
 */
val emptyLoadingState = LoadingState(R.drawable.ic_page_error, "没有数据", "刷新试试", "点击刷新")
val netErrorLoadingState = LoadingState(R.drawable.ic_page_error, "网络出错了", "刷新试试", "点击刷新")
val errorLoadingState = LoadingState(R.drawable.ic_page_error, "出错了", "刷新试试", "点击刷新")

interface LoadingUIState {
    //0、成功  1、加载  2、空  3、网络  4、异常
    val status: MutableLiveData<Int>

    fun getLoadingState(status: Int): LoadingState = when (this.status.value) {
        2 -> emptyLoadingState
        3 -> netErrorLoadingState
        else -> errorLoadingState
    }

    fun onBtnClick(v: View): Unit {}
}

class LoadingState(val img: Int, val title: String, val hint: String, val btn: String)