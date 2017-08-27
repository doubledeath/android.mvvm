package com.github.doubledeath.android.mvvm

/**
 * Created by doubledeath on 8/26/17.
 */
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

    fun setViewModelFactory(viewModelFactory: MvvmViewModelFactory) {
        this.viewModelFactory = viewModelFactory
    }

    @Suppress("UNCHECKED_CAST")
    fun <V : MvvmView, VM : MvvmViewModel<V>> getViewModel(viewModelTag: String): VM {
        val viewModel: MvvmViewModel<*>

        if (viewModelMap.contains(viewModelTag)) {
            viewModel = viewModelMap.getValue(viewModelTag)
        } else {
            viewModel = viewModelFactory.createViewModel(viewModelTag)

            viewModelMap[viewModelTag] = viewModel
        }

        return viewModel as VM
    }

    fun removeViewModel(viewModelTag: String) {
        viewModelMap.remove(viewModelTag)
    }

}