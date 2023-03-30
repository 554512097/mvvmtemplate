package com.bt.apptemplate.uistate

import me.tatarka.bindingcollectionadapter2.OnItemBind

/**
 * Author: kevin
 * Date: 2023/3/15
 * Description:
 */
interface ListUIState {
    val items: List<Any>

    val itemBinding: OnItemBind<Any>
}