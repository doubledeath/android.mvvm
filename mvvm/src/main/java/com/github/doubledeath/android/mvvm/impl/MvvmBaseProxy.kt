package com.github.doubledeath.android.mvvm.impl

import android.os.Bundle
import com.github.doubledeath.android.mvvm.MvvmFacade
import com.github.doubledeath.android.mvvm.MvvmView
import com.github.doubledeath.android.mvvm.MvvmViewModel
import java.util.*
import kotlin.reflect.KClass

internal abstract class MvvmBaseProxy<out VM : MvvmViewModel> {

    private val KEY_TAG: String = "KEY_TAG"

    private var viewModel: VM? = null
    private lateinit var tag: String

    @Suppress("UNCHECKED_CAST")
    internal fun viewModel(klass: KClass<out MvvmView>): VM {
        val viewModel = this.viewModel ?: MvvmFacade.viewModelHost.get(MvvmFacade.viewMapper.viewToViewModel(klass), tag)

        if (this.viewModel === null) {
            this.viewModel = viewModel as VM
        }

        return viewModel as VM
    }

    internal fun onCreate(savedInstanceState: Bundle?) {
        tag = savedInstanceState?.getString(KEY_TAG) ?: generateTag()
    }

    internal open fun onViewActive() {
        viewModel?.onViewActive()
    }

    internal fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_TAG, tag)
    }

    internal open fun onViewInactive() {
        viewModel?.onViewInactive()
    }

    internal open fun onDestroy() {
        MvvmFacade.viewModelHost.remove(tag)
    }

    protected open fun generateTag(): String {
        return UUID.randomUUID().toString()
    }

}