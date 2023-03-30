package com.bt.apptemplate.uistate

/**
 * Author: kevin
 * Date: 2023/3/29
 * Description:
 */
interface ResultState {
    val keys: List<Int>

    fun onResult(result: Pair<Int, Any>)
}