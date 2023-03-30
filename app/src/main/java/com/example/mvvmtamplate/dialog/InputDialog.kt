package com.example.mvvmtamplate.dialog

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bt.apptemplate.baseui.BindingDialog
import com.bt.apptemplate.uistate.PopState
import com.bt.apptemplate.uistate.PopStateArgs
import com.example.mvvmtamplate.R
import com.example.mvvmtamplate.databinding.DialogInputBinding

/**
 * Author: kevin
 * Date: 2023/3/20
 * Description:
 */
class InputDialog : BindingDialog<InputDialogVM, DialogInputBinding>() {

    override fun getLayoutRes(): Int = R.layout.dialog_input
}

class InputDialogVM : ViewModel(), PopState {
    val input = MutableLiveData<String>()
    //override val dialogAction: MutableLiveData<DialogAction> = MutableLiveData()

    fun onEnsureClick(v: View): Unit {
//        dialogAction.value = DialogAction(OK, input.value)
        popState.value = PopStateArgs(resultData = input.value)
    }

    fun onCloseClick(v: View): Unit {
//        dialogAction.value = DialogAction(CANCEL)
        popState.value = PopStateArgs()
    }

    override val popState: MutableLiveData<PopStateArgs> = MutableLiveData()
}