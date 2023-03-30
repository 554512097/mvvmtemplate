package com.example.mvvmtamplate.page

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.TimeUtils
import com.bt.apptemplate.baseui.BindingFragment
import com.bt.apptemplate.uistate.PopState
import com.bt.apptemplate.uistate.PopStateArgs
import com.example.mvvmtamplate.R
import com.example.mvvmtamplate.databinding.FragmentStartResultBinding

/**
 * Author: kevin
 * Date: 2023/3/21
 * Description:
 */
class StartResultPage : BindingFragment<StartResultVM, FragmentStartResultBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_start_result
}

class StartResultVM : ViewModel(), PopState {
    val input = MutableLiveData<String>()
    override val popState: MutableLiveData<PopStateArgs> = MutableLiveData()

    fun onClick(v: View): Unit {
        popState.value = PopStateArgs(
            resultData = mapOf(
                "input" to input.value,
                "time" to TimeUtils.getNowString()
            )
        )
    }
}