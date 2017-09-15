package com.github.doubledeath.android.mvvm.impl

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.base.MvvmBaseActivity
import com.github.doubledeath.android.mvvm.base.MvvmBaseDelegate
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal class MvvmActivityDelegate<VM : MvvmActivityViewModel, B : ViewDataBinding, A : MvvmBaseActivity<VM, B>>
/*constructor*/(private val activity: A, navigator: MvvmAppNavigator) : MvvmBaseDelegate<VM, B, MvvmBaseActivity<*, *>>
/*super.constructor*/(MvvmFacade.viewMapper.viewToViewModel(activity::class) as KClass<VM>, navigator) {

    private var binding: B? = null
    private var context: MvvmBaseActivity<*, *>? = null

    override fun binding(): B {
        val binding = this.binding ?: DataBindingUtil.setContentView(activity, activity.providedLayoutId)

        if (this.binding === null) {
            binding.setVariable(activity.providedViewModelId, viewModel())

            this.binding = binding
        }

        return binding
    }

    override fun context(): MvvmBaseActivity<*, *> {
        val context = this.context ?: activity

        if (this.context === null) {
            this.context = context
        }

        return context
    }

    override fun onDestroy() {
        if (activity.isFinishing) {
            super.onDestroy()
        }
    }

}
