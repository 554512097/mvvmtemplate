package com.bt.apptemplate.uistate

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * Author: kevin
 * Date: 2023/3/2
 * Description:
 */
interface NavState {
    val navState: MutableLiveData<NavStateArgs>
}

class NavStateArgs(
    @IdRes val resId: Int,
    val args: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navigatorExtras: Navigator.Extras? = null
)