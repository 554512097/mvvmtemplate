package com.bt.apptemplate.observer

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Author: kevin
 * Date: 2023/3/23
 * Description:
 */
interface ViewLifecycleObserver {
    fun onViewCreate(owner: LifecycleOwner) {}
    fun onViewStart(owner: LifecycleOwner) {}
    fun onViewResume(owner: LifecycleOwner) {}
    fun onViewPause(owner: LifecycleOwner) {}
    fun onViewStop(owner: LifecycleOwner) {}
    fun onViewDestroy(owner: LifecycleOwner) {}
}

interface FragmentLifecycleObserver {
    fun onFragmentCreate(owner: LifecycleOwner) {}
    fun onFragmentStart(owner: LifecycleOwner) {}
    fun onFragmentResume(owner: LifecycleOwner) {}
    fun onFragmentPause(owner: LifecycleOwner) {}
    fun onFragmentStop(owner: LifecycleOwner) {}
    fun onFragmentDestroy(owner: LifecycleOwner) {}
}

class FragmentLifecycleObserverWrapper(private val observer: FragmentLifecycleObserver) :
    DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        observer.onFragmentCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        observer.onFragmentStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        observer.onFragmentResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        observer.onFragmentPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        observer.onFragmentStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        observer.onFragmentDestroy(owner)
    }
}

class ViewLifecycleObserverWrapper(private val observer: ViewLifecycleObserver) :
    DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        observer.onViewCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        observer.onViewStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        observer.onViewResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        observer.onViewPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        observer.onViewStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        observer.onViewDestroy(owner)
    }
}