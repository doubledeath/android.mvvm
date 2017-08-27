package com.github.doubledeath.android.mvvm.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import com.github.doubledeath.android.mvvm.getTagForFragment

@Suppress("UNUSED")
abstract class MvvmActivity<in V : MvvmView, out VM : MvvmViewModel<V>> : AppCompatActivity(), MvvmView {

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
        if (isFinishing) {
            getViewModel().onDestroy()
        }

        super.onDestroy()
    }

    protected fun getViewModel(): VM {
        val viewModel: VM = this.viewModel ?: MvvmFacade.instance.getViewModel(this::class.getTagForFragment())

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

}