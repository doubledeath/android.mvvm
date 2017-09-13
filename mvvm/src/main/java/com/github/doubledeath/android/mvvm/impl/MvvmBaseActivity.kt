package com.github.doubledeath.android.mvvm.impl

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel

abstract class MvvmBaseActivity<VM : MvvmViewModel, B : ViewDataBinding> : AppCompatActivity(), MvvmView {

    abstract val providedViewModelId: Int @IdRes get
    abstract val providedLayoutId: Int @LayoutRes get
    protected lateinit var viewModel: VM
        private set
    protected lateinit var binding: B
        private set
    private var proxy: MvvmActivityProxy<VM, B, MvvmBaseActivity<VM, B>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        proxy().onCreate(savedInstanceState)

        viewModel = proxy().viewModel()
        binding = proxy().binding()
    }

    override fun onResume() {
        super.onResume()

        proxy().onViewActive()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        proxy().onSaveInstanceState(outState)
    }

    override fun onPause() {
        proxy().onViewInactive()

        super.onPause()
    }

    override fun onDestroy() {
        proxy().onDestroy()

        super.onDestroy()
    }

    private fun proxy(): MvvmActivityProxy<VM, B, MvvmBaseActivity<VM, B>> {
        val proxy = this.proxy ?: MvvmActivityProxy(this)

        if (this.proxy === null) {
            this.proxy = proxy
        }

        return proxy
    }
}