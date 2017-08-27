package com.github.doubledeath.android.mvvm.impl

import android.app.Fragment
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel

@Suppress("UNUSED")
abstract class MvvmFragment<in V : MvvmView, out VM : MvvmViewModel<V>> : Fragment(), MvvmView {

    private var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().onCreate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResume() {
        super.onResume()

        getViewModel().onAttachView(this as V)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onPause() {
        getViewModel().onDetachView(this as V)

        super.onPause()
    }

    override fun onDestroy() {
        if (isRemoving) {
            getViewModel().onDestroy()
        }

        super.onDestroy()
    }

    protected fun getViewModel(): VM {
        val viewModel: VM = this.viewModel ?: MvvmFacade.instance.getViewModel(tag)

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

    companion object {
        var tag: String = this::class.toString()
            private set
    }

}
