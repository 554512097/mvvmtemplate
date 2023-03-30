package com.example.mvvmtamplate.page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bt.apptemplate.TAG
import com.bt.apptemplate.baseui.BindingFragment
import com.bt.apptemplate.uistate.LoadingUIState
import com.bt.apptemplate.uistate.TitleUIState
import com.example.mvvmtamplate.R
import com.example.mvvmtamplate.databinding.FragmentLoadingStateBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Author: kevin
 * Date: 2023/3/16
 * Description:
 */
class LoadingStatePage : BindingFragment<LoadingStateVM, FragmentLoadingStateBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_loading_state
}

class LoadingStateVM : ViewModel(), TitleUIState, LoadingUIState {
    override val title: MutableLiveData<String> = MutableLiveData("loading state page")
    override val status: MutableLiveData<Int> = MutableLiveData(1)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            status.value = getData()
            Log.d(TAG, "LoadingStatePage: -------------")
        }
    }

    private suspend fun getData(): Int {
        delay(2000)
        return 0
    }
}