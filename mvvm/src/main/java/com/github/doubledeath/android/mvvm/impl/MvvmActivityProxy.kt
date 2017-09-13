package com.github.doubledeath.android.mvvm.impl

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import com.github.doubledeath.android.mvvm.MvvmApp
import com.github.doubledeath.android.mvvm.MvvmViewModel

internal class MvvmActivityProxy<VM : MvvmViewModel, B : ViewDataBinding, in A : MvvmBaseActivity<VM, B>>
constructor(private val activity: A) : MvvmBaseProxy<VM>() {

    private var binding: B? = null

    override fun onViewActive() {
        MvvmApp.navigator.pool.put(activity::class, activity)

        super.onViewActive()
    }

    override fun onViewInactive() {
        super.onViewInactive()

        MvvmApp.navigator.pool.remove(activity::class)
    }

    override fun onDestroy() {
        if (!MvvmApp.navigator.getCurrentModel().isSingle && activity.isFinishing) {
            super.onDestroy()
        }
    }

    override fun generateTag(): String {
        return if (MvvmApp.navigator.getCurrentModel().isSingle) {
            activity::class.toString()
        } else {
            super.generateTag()
        }
    }

    internal fun viewModel(): VM {
        return viewModel(activity::class)
    }

    internal fun binding(): B {
        val binding = this.binding ?: DataBindingUtil.setContentView(activity, activity.providedLayoutId)

        if (this.binding === null) {
            binding.setVariable(activity.providedViewModelId, viewModel())

            this.binding = binding
        }

        return binding
    }

}