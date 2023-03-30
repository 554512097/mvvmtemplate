package com.example.mvvmtamplate.page

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bt.apptemplate.baseui.BindingFragment
import com.bt.apptemplate.uistate.ListUIState
import com.bt.apptemplate.uistate.LoadingUIState
import com.bt.apptemplate.uistate.TitleUIState
import com.example.mvvmtamplate.BR
import com.example.mvvmtamplate.R
import com.example.mvvmtamplate.databinding.FragmentListPageBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

/**
 * Author: kevin
 * Date: 2023/3/17
 * Description:
 */
class ListPage : BindingFragment<ListVM, FragmentListPageBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_list_page
}

class ListVM : ViewModel(), ListUIState, TitleUIState, LoadingUIState {
    override val status: MutableLiveData<Int> = MutableLiveData(1)
    override val title: MutableLiveData<String> = MutableLiveData("list page")
    override val items: ObservableArrayList<Any> = ObservableArrayList()
    override val itemBinding: OnItemBindClass<Any> = OnItemBindClass<Any>()

    init {
        itemBinding.map(String::class.java, BR.vm, R.layout.item_single_text)

        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(2000)
            flow<String> {
                for (i in 0..20) {
                    emit("item $i")
                    delay(500)
                }
            }.onStart {
                status.value = 0
            }.collect {
                items.add(it)
            }
        }
    }
}