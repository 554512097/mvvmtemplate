package com.bt.apptemplate.uistate

import androidx.lifecycle.MutableLiveData

/**
 * Author: kevin
 * Date: 2023/3/21
 * Description:
 */
interface DialogActionState {
    val dialogAction: MutableLiveData<DialogAction>
}

class DialogAction(
    val key: String, val extra: Any? = null
)