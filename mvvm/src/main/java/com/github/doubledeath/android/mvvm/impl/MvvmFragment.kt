package com.github.doubledeath.android.mvvm.impl

import android.app.Fragment
import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmDelegate
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel

@Suppress("UNUSED")
abstract class MvvmFragment<in V : MvvmView, out VM : MvvmViewModel<V>> : Fragment(), MvvmView {

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
        if (isRemoving) {
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
