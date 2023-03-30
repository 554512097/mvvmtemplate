package com.bt.apptemplate.baseui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bt.apptemplate.BR
import com.bt.apptemplate.uistate.DialogAction
import com.bt.apptemplate.uistate.DialogActionState
import com.bt.apptemplate.uistate.ONLY_POP_STACK
import com.bt.apptemplate.uistate.PopState
import java.lang.reflect.ParameterizedType

/**
 * Author: kevin
 * Date: 2023/3/20
 * Description:
 */

const val OK = "ok"
const val CANCEL = "cancel"

abstract class BindingDialog<VM : ViewModel, DB : ViewDataBinding> : DialogFragment() {
    protected lateinit var navController: NavController
    protected lateinit var binding: DB
    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
        getVM()
        if (vm is DialogActionState) {
            val actionState = vm as DialogActionState
            actionState.dialogAction.observe(this) {
                when (it.key) {
                    OK -> {
                        dismiss()
                    }
                    CANCEL -> dismiss()
                }
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initWindow()
    }

    open fun initWindow() {
        val layoutParams = dialog?.window?.attributes
        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = layoutParams
    }

    abstract fun getLayoutRes(): Int

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

interface OnActionListener {
    fun onAction(action: DialogAction)
}