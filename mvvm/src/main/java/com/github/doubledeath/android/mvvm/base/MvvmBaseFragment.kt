package com.github.doubledeath.android.mvvm.base

import android.app.Fragment
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.impl.MvvmFragmentDelegate
import com.github.doubledeath.android.mvvm.impl.MvvmFragmentViewModel

abstract class MvvmBaseFragment<VM : MvvmFragmentViewModel, B : ViewDataBinding> : Fragment(), MvvmView {

    abstract val providedLayoutId: Int @LayoutRes get
    abstract val providedViewModelId: Int @IdRes get
    protected lateinit var viewModel: VM
        private set
    protected lateinit var binding: B
        private set
    private var mvvmDelegate: MvvmFragmentDelegate<VM, B, MvvmBaseFragment<VM, B>>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        delegate().onCreate(arguments, savedInstanceState)

        viewModel = delegate().viewModel()
        binding = delegate().binding()

        return binding.root
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

    private fun delegate(): MvvmFragmentDelegate<VM, B, MvvmBaseFragment<VM, B>> {
        val delegate = mvvmDelegate ?: MvvmFragmentDelegate(this, (activity as MvvmBaseActivity<*, *>).delegate().selfNavigator())

        if (mvvmDelegate === null) {
            mvvmDelegate = delegate
        }

        return delegate
    }

}
