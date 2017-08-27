package com.github.doubledeath.android.mvvm.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.*

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
        val viewModel: VM = this.viewModel ?: MvvmFacade.instance.getViewModel(this::class.getTagForActivity())

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

}