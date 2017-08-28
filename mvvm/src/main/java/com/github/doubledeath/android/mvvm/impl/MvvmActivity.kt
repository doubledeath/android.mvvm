package com.github.doubledeath.android.mvvm.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.doubledeath.android.mvvm.MvvmDelegate
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel

@Suppress("UNUSED")
abstract class MvvmActivity<in V : MvvmView, out VM : MvvmViewModel<V>> : AppCompatActivity(), MvvmView {

    private var mvvmDelegate: MvvmDelegate<V, VM>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMvvmDelegate().onCreate(savedInstanceState)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResume() {
        super.onResume()

        getMvvmDelegate().onViewActive(this as V)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        getMvvmDelegate().onSaveInstanceState(outState)
        getMvvmDelegate().onViewInactive(this as V)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onPause() {
        getMvvmDelegate().onViewInactive(this as V)

        super.onPause()
    }

    override fun onDestroy() {
        if (isFinishing) {
            getMvvmDelegate().onDestroy()
        }

        super.onDestroy()
    }

    protected fun getViewModel(): VM {
        return getMvvmDelegate().getViewModel()
    }

    private fun getMvvmDelegate(): MvvmDelegate<V, VM> {
        val mvvmDelegate: MvvmDelegate<V, VM> = this.mvvmDelegate ?: MvvmDelegate(this::class)

        if (this.mvvmDelegate === null) {
            this.mvvmDelegate = mvvmDelegate
        }

        return mvvmDelegate
    }

}