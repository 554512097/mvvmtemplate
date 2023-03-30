package com.bt.apptemplate.baseui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bt.apptemplate.BR
import com.bt.apptemplate.observer.FragmentLifecycleObserver
import com.bt.apptemplate.observer.FragmentLifecycleObserverWrapper
import com.bt.apptemplate.observer.ViewLifecycleObserver
import com.bt.apptemplate.observer.ViewLifecycleObserverWrapper
import com.bt.apptemplate.uistate.*
import java.lang.reflect.ParameterizedType

/**
 * Author: kevin
 * Date: 2023/2/27
 * Description:
 */
abstract class BindingFragment<VM : ViewModel, DB : ViewDataBinding> : Fragment() {
    protected lateinit var navController: NavController
    protected lateinit var binding: DB
    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
        vm = getVM()
        if (vm is NavState) {
            (vm as NavState).navState.observe(this) {
                navController.navigate(it.resId, it.args, it.navOptions, it.navigatorExtras)
            }
        }
        if (vm is PopState) {
            (vm as PopState).popState.observe(this) { resultArgs ->
                resultArgs.resultData?.also { it ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        navController.currentDestination!!.id.toString(),
                        it
                    )
                }
                if (resultArgs.destinationId == ONLY_POP_STACK)
                    navController.popBackStack()
                else
                    navController.popBackStack(
                        resultArgs.destinationId,
                        resultArgs.inclusive,
                        resultArgs.saveState
                    )
            }
        }
        if (vm is ToastState) {
            (vm as ToastState).toastState.observe(this) {
                Toast.makeText(context, it.msg, it.duration).show()
            }
        }
        if (vm is FragmentLifecycleObserver) {
            lifecycle.addObserver(FragmentLifecycleObserverWrapper(vm as FragmentLifecycleObserver))
        }
        if (vm is ResultState) {
            val resultState = vm as ResultState
            resultState.keys.forEach { key ->
                navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Any>(key.toString())
                    ?.observe(this) { value ->
                        resultState.onResult(key to value)
                    }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (vm is ViewLifecycleObserver) {
            viewLifecycleOwner.lifecycle.addObserver(ViewLifecycleObserverWrapper(vm as ViewLifecycleObserver))
        }
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutRes(),
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.vm, vm)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    protected abstract fun getLayoutRes(): Int

    open fun getVM(): VM {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val types = type.actualTypeArguments
            val vmType = types[0]
            vm = ViewModelProvider(this).get<VM>((vmType as Class<VM>))
        }
        return vm
    }
}
