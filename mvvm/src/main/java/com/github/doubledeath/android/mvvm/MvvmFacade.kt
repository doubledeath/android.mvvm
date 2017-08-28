package com.github.doubledeath.android.mvvm

import kotlin.reflect.KClass

class MvvmFacade private constructor() {

    private object Holder {
        val INSTANCE = MvvmFacade()
    }

    companion object {
        val instance: MvvmFacade by lazy { Holder.INSTANCE }
    }

    private val viewModelMap: MutableMap<String, MvvmViewModel<*>>
    private lateinit var viewModelFactory: MvvmViewModelFactory

    init {
        viewModelMap = HashMap()
    }

    @Suppress("UNUSED")
    fun setViewModelFactory(viewModelFactory: MvvmViewModelFactory) {
        this.viewModelFactory = viewModelFactory
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <V : MvvmView, VM : MvvmViewModel<V>> getViewModel(klass: KClass<*>, tag: String): VM {
        val viewModel: MvvmViewModel<*>

        if (viewModelMap.contains(tag)) {
            viewModel = viewModelMap.getValue(tag)
        } else {
            viewModel = viewModelFactory.createViewModel(klass as KClass<V>)

            viewModelMap[tag] = viewModel
        }

        return viewModel as VM
    }

    internal fun removeViewModel(tag: String) {
        viewModelMap.remove(tag)
    }

}