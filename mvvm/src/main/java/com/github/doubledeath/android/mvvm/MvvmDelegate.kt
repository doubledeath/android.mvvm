package com.github.doubledeath.android.mvvm

import android.os.Bundle
import java.util.*
import kotlin.reflect.KClass

internal class MvvmDelegate<in V : MvvmView, out VM : MvvmViewModel<V>> constructor(private val klass: KClass<*>) {

    private val KEY_TAG: String = "KEY_TAG"

    private var viewModel: VM? = null
    private lateinit var tag: String

    internal fun onCreate(savedInstanceState: Bundle?) {
        tag = savedInstanceState?.getString(KEY_TAG) ?: generateTag()
    }

    internal fun onViewActive() {
        getViewModel().onViewActive()
    }

    internal fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_TAG, tag)
    }

    internal fun onViewInactive() {
        getViewModel().onViewInactive()
    }

    internal fun onDestroy() {
        MvvmFacade.instance.removeViewModel(tag)
    }

    internal fun getViewModel(): VM {
        val viewModel: VM = this.viewModel ?: MvvmFacade.instance.getViewModel(klass, tag)

        if (this.viewModel === null) {
            this.viewModel = viewModel
        }

        return viewModel
    }

    private fun generateTag(): String {
        return UUID.randomUUID().toString()
    }

}