package com.github.doubledeath.android.mvvm.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.MvvmApp
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.impl.MvvmActivityDelegate
import com.github.doubledeath.android.mvvm.impl.MvvmActivityViewModel

abstract class MvvmBaseActivity<VM : MvvmActivityViewModel, B : ViewDataBinding> : AppCompatActivity(), MvvmView {

    abstract val providedLayoutId: Int @LayoutRes get
    abstract val providedViewModelId: Int @IdRes get
    protected lateinit var viewModel: VM
        private set
    protected lateinit var binding: B
        private set
    private var mvvmDelegate: MvvmActivityDelegate<VM, B, MvvmBaseActivity<VM, B>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        delegate().onCreate(intent?.extras, savedInstanceState)

        viewModel = delegate().viewModel()
        binding = delegate().binding()
    }

    override fun onResume() {
        super.onResume()

        delegate().onViewActive()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        delegate().onSaveInstanceState(outState)
    }

    override fun onPause() {
        delegate().onViewInactive()

        super.onPause()
    }

    override fun onDestroy() {
        delegate().onDestroy()

        super.onDestroy()
    }

    private fun delegate(): MvvmActivityDelegate<VM, B, MvvmBaseActivity<VM, B>> {
        val delegate = mvvmDelegate ?: MvvmActivityDelegate(this, MvvmApp.navigator)

        if (mvvmDelegate === null) {
            mvvmDelegate = delegate
        }

        return delegate
    }

}
