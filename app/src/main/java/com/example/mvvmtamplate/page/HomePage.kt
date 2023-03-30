package com.example.mvvmtamplate.page

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bt.apptemplate.TAG
import com.bt.apptemplate.baseui.BindingFragment
import com.bt.apptemplate.observer.FragmentLifecycleObserver
import com.bt.apptemplate.observer.ViewLifecycleObserver
import com.bt.apptemplate.uistate.*
import com.example.mvvmtamplate.R
import com.example.mvvmtamplate.databinding.FragmentHomeBinding
import com.example.mvvmtamplate.net.ApiService
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Author: kevin
 * Date: 2023/3/16
 * Description:
 */
class HomePage : BindingFragment<HomeVM, FragmentHomeBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_home
}

class HomeVM : ViewModel(), NavState, ToastState, FragmentLifecycleObserver,
    ViewLifecycleObserver, ResultState {

    override val keys: List<Int> = listOf(
        R.id.startResultPage,
        R.id.inputDialog,
    )

    override fun onResult(result: Pair<Int, Any>) {
        when (result.first) {
            R.id.startResultPage -> {
                val resultMap = result.second as Map<String, String>
                toastState.value =
                    ToastStateArgs("input:${resultMap["input"]}----time:${resultMap["time"]}")
            }
            R.id.inputDialog -> {
                toastState.value = ToastStateArgs(result.second.toString())
            }
        }
    }

    fun onClick(tag: Int): Unit {
        when (tag) {
            1 -> {
                navState.value = NavStateArgs(R.id.action_homePage_to_loadingStatePage)
            }
            2 -> {
                navState.value = NavStateArgs(R.id.action_homePage_to_listPage)
            }
            3 -> {
                navState.value = NavStateArgs(R.id.action_homePage_to_startResultPage)
            }
            4 -> {
                navState.value = NavStateArgs(R.id.inputDialog)
            }
            5 -> {
                viewModelScope.launch {
                    ApiService.userApi.userList()
                        .map { it.data }
                        .collect {
                        }
                }
            }
            else -> {
            }
        }
    }

    override val navState: MutableLiveData<NavStateArgs> = MutableLiveData()
    override val toastState: MutableLiveData<ToastStateArgs> = MutableLiveData()

    override fun onViewCreate(owner: LifecycleOwner) {
        Log.d(TAG, "onViewCreate: ---")
    }

    override fun onViewStart(owner: LifecycleOwner) {
        Log.d(TAG, "onViewStart: ---")
    }

    override fun onViewResume(owner: LifecycleOwner) {
        Log.d(TAG, "onViewResume: ---")
    }

    override fun onViewPause(owner: LifecycleOwner) {
        Log.d(TAG, "onViewPause: ---")
    }

    override fun onViewStop(owner: LifecycleOwner) {
        Log.d(TAG, "onViewStop: ---")
    }

    override fun onViewDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "onViewDestroy: ---")
    }

    override fun onFragmentCreate(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentCreate: ---")
    }

    override fun onFragmentStart(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentStart: ---")
    }

    override fun onFragmentResume(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentResume: ---")
    }

    override fun onFragmentPause(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentPause: ---")
    }

    override fun onFragmentStop(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentStop: ---")
    }

    override fun onFragmentDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "onFragmentDestroy: ---")
    }
}
