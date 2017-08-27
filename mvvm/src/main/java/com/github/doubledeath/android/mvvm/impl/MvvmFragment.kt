package com.github.doubledeath.android.mvvm.impl

import android.app.Fragment
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel

/**
 * Created by doubledeath on 8/26/17.
 */
open class MvvmFragment<in V : MvvmView, out VM : MvvmViewModel<V>> : Fragment(), MvvmView {

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
        val viewModel: VM = this.viewModel ?: MvvmFacade.instance.getViewModel(this::class.toString())

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

}
